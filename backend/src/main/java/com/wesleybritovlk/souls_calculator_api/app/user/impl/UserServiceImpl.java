package com.wesleybritovlk.souls_calculator_api.app.user.impl;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Supplier;

import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.wesleybritovlk.souls_calculator_api.app.user.UserEntity;
import com.wesleybritovlk.souls_calculator_api.app.user.UserProjection;
import com.wesleybritovlk.souls_calculator_api.app.user.UserRepository;
import com.wesleybritovlk.souls_calculator_api.app.user.UserService;
import com.wesleybritovlk.souls_calculator_api.app.user.dto.UserRequest;
import com.wesleybritovlk.souls_calculator_api.app.user.dto.UserResponse.Full;
import com.wesleybritovlk.souls_calculator_api.app.user.dto.UserResponse.Update;
import com.wesleybritovlk.souls_calculator_api.core.uuidmask.UUIDMask;
import com.wesleybritovlk.souls_calculator_api.infra.util.exception.ExceptionMessages;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper mapper;
    private final UserRepository repository;
    private final UUIDMask mask;

    @Override
    public UserEntity save(UserRequest.Create request) {
        if (repository.existsByEmailAndDeletedAtNull(request.email()))
            throw new ResponseStatusException(HttpStatus.CONFLICT, ExceptionMessages.CONFLICT_USER);
        UserEntity entity = mapper.toEntity(request);
        return repository.saveAndFlush(entity);
    }

    @Override
    public Optional<UserProjection.Auth> findAuth(String email) {
        return repository.findAuthByEmail(email);
    }

    private Supplier<ResponseStatusException> unauthorizedThrow() {
        return () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, ExceptionMessages.UNAUTHORIZED_TOKEN);
    }

    private UUID extractUserIdByToken(JwtAuthenticationToken token) {
        return mask.getOriginalId(token.getToken().getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, ExceptionMessages.UNAUTHORIZED_TOKEN));
    }

    @Override
    public Full find(JwtAuthenticationToken token) {
        UUID userId = this.extractUserIdByToken(token);
        UserEntity entity = repository.findByIdAndDeletedAtNull(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ExceptionMessages.USER_NOT_FOUND));
        return mapper.toResponse(entity);
    }

    @Override
    public Update update(JwtAuthenticationToken token, UserRequest.Update request) {
        UUID userId = this.extractUserIdByToken(token);
        if (repository.existsByEmailAndIdNotAndDeletedAtNull(request.email(), userId))
            throw new ResponseStatusException(HttpStatus.CONFLICT, ExceptionMessages.CONFLICT_USER);
        int update = repository.update(userId, request, ZonedDateTime.now());
        if (update < 1) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ExceptionMessages.FALIED_UPDATE);
        }
        return mapper.toResponse(request);
    }

    @Override
    public void delete(JwtAuthenticationToken token) {
        UUID userId = mask.removeOriginalId(token.getToken().getId())
                .orElseThrow(unauthorizedThrow());
        int softDelete = repository.softDeleteByUserId(userId, ZonedDateTime.now());
        if (softDelete < 1) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ExceptionMessages.FALIED_DELETE);
        }
    }

}
