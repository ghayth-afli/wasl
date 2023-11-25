package com.mzo.wasl.service;

import com.mzo.wasl.model.ERole;
import com.mzo.wasl.model.Role;
import com.mzo.wasl.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Optional<Role> getRoleByName(ERole name) {
        return roleRepository.findByName(name);
    }
}
