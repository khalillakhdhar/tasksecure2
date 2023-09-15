package com.demonstration.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demonstration.entities.AppUser;


public interface UserRepository extends JpaRepository<AppUser, String> {
	public AppUser findByUsername(String username);
}