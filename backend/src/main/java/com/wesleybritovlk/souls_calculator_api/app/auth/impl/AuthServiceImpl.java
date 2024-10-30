package com.wesleybritovlk.souls_calculator_api.app.auth.impl;

import java.util.UUID;
import java.util.function.Supplier;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.wesleybritovlk.souls_calculator_api.app.auth.AuthService;
import com.wesleybritovlk.souls_calculator_api.app.auth.dto.AuthPayload.Token;
import com.wesleybritovlk.souls_calculator_api.app.auth.dto.AuthRequest;
import com.wesleybritovlk.souls_calculator_api.app.auth.dto.AuthResponse;
import com.wesleybritovlk.souls_calculator_api.app.user.UserEntity;
import com.wesleybritovlk.souls_calculator_api.app.user.UserService;
import com.wesleybritovlk.souls_calculator_api.app.user.dto.UserRequest.Create;
import com.wesleybritovlk.souls_calculator_api.core.role.RoleEntity;
import com.wesleybritovlk.souls_calculator_api.core.role.RoleEntity.Role;
import com.wesleybritovlk.souls_calculator_api.core.role.RoleRepository;
import com.wesleybritovlk.souls_calculator_api.core.uuidmask.UUIDMask;
import com.wesleybritovlk.souls_calculator_api.infra.util.exception.ExceptionMessages;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
        private final AuthMapper mapper;
        private final AuthTokenHelper tokenHelper;
        private final UserService userService;
        private final RoleRepository roleRepo;
        private final PasswordEncoder passwordEncoder;
        private final UUIDMask mask;

        private Supplier<ResponseStatusException> unauthorizedThrow() {
                return () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, ExceptionMessages.UNAUTHORIZED_TOKEN);
        }

        @Override
        public AuthResponse login(AuthRequest.Login request) {
                var projection = userService.findAuth(request.email());
                boolean matches = false;
                if (projection.isPresent())
                        matches = passwordEncoder.matches(request.password(), projection.get().getPassword());
                if (projection.isEmpty() || !matches)
                        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "email or password is invalid!");
                Token payload = mapper.toPayload(request, projection);
                return tokenHelper.generateToken(payload);
        }

        @Override
        public AuthResponse register(AuthRequest.Register request) {
                String password = passwordEncoder.encode(request.password());
                RoleEntity role = roleRepo.findByName(Role.USER).orElseThrow();
                Create userRequest = mapper.toRequest(request, password, role);
                UserEntity entity = userService.save(userRequest);
                Token payload = mapper.toPayload(request, entity);
                return tokenHelper.generateToken(payload);
        }

        @Override
        public AuthResponse refreshToken(JwtAuthenticationToken token) {
                UUID userId = mask.getOriginalId(token.getToken().getId()).orElseThrow(unauthorizedThrow());
                Token payload = mapper.toPayload(userId, token);
                return tokenHelper.generateToken(payload);
        }

        @Override
        public void logout(JwtAuthenticationToken token) {
                mask.removeOriginalId(token.getToken().getId()).orElseThrow(unauthorizedThrow());
        }
}
