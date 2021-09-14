package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.filters.AppRole;

@Repository
public interface AppRoleRepository extends JpaRepository<AppRole, Integer>{

	public AppRole findByRoleName(String roleName);
}
