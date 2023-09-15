package com.demonstration.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.demonstration.dao.RoleRepository;
import com.demonstration.dao.UserRepository;
import com.demonstration.entities.AppRole;
import com.demonstration.entities.AppUser;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public AccountServiceImpl(
        BCryptPasswordEncoder bCryptPasswordEncoder,
        UserRepository userRepository,
        RoleRepository roleRepository
    ) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public AppUser saveUser(AppUser user) {
        String hashPW = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashPW);
        return userRepository.save(user);
    }

    @Override
    public AppRole saveRole(AppRole role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppRole role = roleRepository.findByRoleName(roleName);
        AppUser user = userRepository.findByUsername(username);
        user.getRoles().add(role);
    }

    @Override
    public AppUser findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
