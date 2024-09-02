package com.wesleybritovlk.souls_calculator_api.core.role;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.wesleybritovlk.souls_calculator_api.core.role.RoleEntity.Role;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@Transactional
@RequiredArgsConstructor
public class RoleFactory implements CommandLineRunner {
    private final RoleRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(RoleFactory.class);

    private static Set<RoleEntity> generateRoles() {
        return Set.of(
                RoleEntity.builder().role(Role.ADMIN).build(),
                RoleEntity.builder().role(Role.USER).build());
    }

    @Override
    public void run(String... args) throws Exception {
        if (repository.count() == 0) {
            Set<RoleEntity> roles = generateRoles();
            repository.saveAll(roles);
            logger.info("Successfully populated the database with {} roles.", roles.size());
        } else
            logger.info("Database already contains roles. Skipping seeding.");
    }

}
