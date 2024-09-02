package com.wesleybritovlk.souls_calculator_api.app.auth;

import java.util.Optional;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.wesleybritovlk.souls_calculator_api.app.auth.dto.AuthPayload;
import com.wesleybritovlk.souls_calculator_api.app.auth.dto.AuthPayload.Token;
import com.wesleybritovlk.souls_calculator_api.app.auth.dto.AuthRequest;
import com.wesleybritovlk.souls_calculator_api.app.auth.dto.AuthResponse;
import com.wesleybritovlk.souls_calculator_api.app.user.UserEntity;
import com.wesleybritovlk.souls_calculator_api.app.user.UserService;
import com.wesleybritovlk.souls_calculator_api.app.user.dto.UserRequest.Create;
import com.wesleybritovlk.souls_calculator_api.core.role.RoleEntity;
import com.wesleybritovlk.souls_calculator_api.core.role.RoleEntity.Role;
import com.wesleybritovlk.souls_calculator_api.core.role.RoleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
        private final AuthMapper mapper;
        private final AuthTokenHelper tokenHelper;
        private final UserService userService;
        private final RoleRepository roleRepo;
        private final PasswordEncoder passwordEncoder;

        public AuthResponse login(AuthRequest.Login request) {
                Optional<UserEntity> entity = userService.findByEmail(request.email());
                boolean matches = false;
                if (entity.isPresent())
                        matches = passwordEncoder.matches(request.password(), entity.get().getPassword());
                if (entity.isEmpty() || !matches)
                        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "email or password is invalid!");
                Token payload = mapper.toPayload(request, entity);
                return tokenHelper.generateToken(payload);
        }

        public AuthResponse register(AuthRequest.Register request) {
                String password = passwordEncoder.encode(request.password());
                RoleEntity role = roleRepo.findByRole(Role.USER);
                Create userRequest = mapper.toRequest(request, password, role);
                UserEntity entity = userService.save(userRequest);
                Token payload = mapper.toPayload(request, entity);
                return tokenHelper.generateToken(payload);
        }

        public AuthResponse refreshToken(JwtAuthenticationToken token) {
                Token payload = mapper.toPayload(token);
                return tokenHelper.generateToken(payload);
        }

}
