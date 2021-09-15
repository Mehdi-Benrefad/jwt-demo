package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.CustomUser;

@Repository
public interface CustumizedUserDao extends JpaRepository<CustomUser, Integer>{
	
	public CustomUser findByUsername(String username);

}
