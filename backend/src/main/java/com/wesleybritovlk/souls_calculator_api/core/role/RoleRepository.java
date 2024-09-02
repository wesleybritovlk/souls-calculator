package com.wesleybritovlk.souls_calculator_api.core.role;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wesleybritovlk.souls_calculator_api.core.role.RoleEntity.Role;

public interface RoleRepository extends JpaRepository<RoleEntity, UUID> {

    RoleEntity findByRole(Role role);
}
