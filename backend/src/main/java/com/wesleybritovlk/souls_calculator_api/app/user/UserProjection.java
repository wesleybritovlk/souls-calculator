package com.wesleybritovlk.souls_calculator_api.app.user;

import java.util.List;
import java.util.UUID;

import com.wesleybritovlk.souls_calculator_api.core.role.RoleProjection;

public class UserProjection {
    public interface Auth {
        UUID getId();

        String getEmail();

        String getPassword();

        List<RoleProjection> getRoles();
    }
}
