package com.mzo.wasl.service;

import com.mzo.wasl.model.ERole;
import com.mzo.wasl.model.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> getRoleByName(ERole name);
}
