package com.wesleybritovlk.souls_calculator_api.app.user;

import java.time.ZonedDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.wesleybritovlk.souls_calculator_api.app.user.dto.UserRequest.Update;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    @Query("SELECT u.id AS id, u.email AS email, u.password AS password, r.name AS roles " +
            "FROM UserEntity u LEFT JOIN u.roles r " +
            "WHERE u.email = :email AND u.deletedAt IS NULL")
    Optional<UserProjection.Auth> findAuthByEmail(String email);

    Optional<UserEntity> findByIdAndDeletedAtNull(UUID userId);

    boolean existsByEmailAndDeletedAtNull(String email);

    boolean existsByEmailAndIdNotAndDeletedAtNull(String email, UUID id);

    @Modifying
    @Query("UPDATE UserEntity u SET " +
            "u.username = :#{#request.username}, " +
            "u.email = :#{#request.email}, " +
            "u.updatedAt = :now WHERE u.id = :id AND u.deletedAt IS NULL")
    int update(UUID id, Update request, ZonedDateTime now);

    @Modifying
    @Query("UPDATE UserEntity u SET u.updatedAt = :now, u.deletedAt = :now WHERE u.id = :id AND u.deletedAt IS NULL")
    int softDeleteByUserId(UUID id, ZonedDateTime now);

}
