package com.mzo.wasl.service;

import com.mzo.wasl.model.ERole;
import com.mzo.wasl.model.Role;
import com.mzo.wasl.model.User;
import com.mzo.wasl.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleService roleService;
    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> getAllRegulars() {
        //get all users with role REGULAR
        Role role = roleService.getRoleByName(ERole.ROLE_REGULAR).get();
        roleService.getRoleByName(ERole.ROLE_REGULAR);
        return userRepository.findAllByRole(role);
    }

    @Override
    public List<User> getAllSupports() {
        Role role = roleService.getRoleByName(ERole.ROLE_SUPPORT).get();
        return userRepository.findAllByRole(role);
    }
}