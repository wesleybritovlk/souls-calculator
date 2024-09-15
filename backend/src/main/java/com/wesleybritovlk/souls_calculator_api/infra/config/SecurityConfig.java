package com.wesleybritovlk.souls_calculator_api.infra.config;

import static org.springframework.security.config.Customizer.withDefaults;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.wesleybritovlk.souls_calculator_api.infra.filter.AccessDeniedFilter;
import com.wesleybritovlk.souls_calculator_api.infra.filter.AuthEntryPointFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
        @Value("${rsa.public-key}")
        private RSAPublicKey publicKey;
        @Value("${rsa.private-key}")
        private RSAPrivateKey privateKey;

        private static final String[] WHITE_LIST = { "/", "api/v1/auth/login/**", "api/v1/auth/register/**" };

        protected void userRequests(
                        AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry request) {
                final String auth = "SCOPE_USER";
                request.requestMatchers(HttpMethod.DELETE, "api/v1/auth/logout").hasAuthority(auth);
                request.requestMatchers(HttpMethod.GET, "api/v1/auth/refresh_token").hasAuthority(auth);
                request.requestMatchers("api/v1/users/**").hasAuthority(auth);
                request.requestMatchers("api/v1/games/**").hasAuthority(auth);
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                return http
                                .csrf(csrf -> csrf.disable())
                                .sessionManagement(session -> session
                                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .authorizeHttpRequests(requests -> {
                                        userRequests(requests);
                                        requests.requestMatchers(HttpMethod.POST, WHITE_LIST).permitAll();
                                        requests.anyRequest().authenticated();
                                })
                                .oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults()))
                                .exceptionHandling(exceptionHandling -> exceptionHandling
                                                .accessDeniedHandler(new AccessDeniedFilter(new ObjectMapper()))
                                                .authenticationEntryPoint(new AuthEntryPointFilter(new ObjectMapper())))
                                .build();
        }

        @Bean
        public JwtDecoder jwtDecoder() {
                return NimbusJwtDecoder.withPublicKey(publicKey).build();
        }

        @Bean
        public JwtEncoder jwtEncoder() {
                JWK jwk = new RSAKey.Builder(this.publicKey).privateKey(privateKey).build();
                var jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
                return new NimbusJwtEncoder(jwks);
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
                return SCryptPasswordEncoder.defaultsForSpringSecurity_v5_8();
        }
}
