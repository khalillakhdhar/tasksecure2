package com.demonstration.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demonstration.entities.AppRole;


public interface RoleRepository extends JpaRepository<AppRole, String> {
	public AppRole findByRoleName(String roleName);
}