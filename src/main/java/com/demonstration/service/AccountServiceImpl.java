package com.demonstration.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
		// TODO Auto-generated method stub
		
		String hashPW=bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(hashPW);
		return userRepository.save(user);
	}

	@Override
	public AppRole saveRole(AppRole role) {
		// TODO Auto-generated method stub
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String username, String roleName) {
		// TODO Auto-generated method stub
AppRole role=roleRepository.findByRoleName(roleName);
AppUser user=userRepository.findByUsername(username);
user.getRoles().add(role);
this.saveUser(user);
	}

	@Override
	public AppUser findUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}



	@Override
	public List<AppUser> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

}
