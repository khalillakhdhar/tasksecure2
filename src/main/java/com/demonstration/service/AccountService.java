package com.demonstration.service;

import com.demonstration.entities.AppRole;
import com.demonstration.entities.AppUser;

public interface AccountService {
	   
	public AppUser saveUser(AppUser user);
	public AppRole saveRole(AppRole role);
	public void addRoleToUser(String username,String roleName);
	public AppUser findUserByUsername(String username);
}