package com.example.demo.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dao.AppRoleRepository;
import com.example.demo.dao.CustumizedUserDao;
import com.example.demo.entities.AppRole;
import com.example.demo.entities.CustomUser;

@Service
@Transactional
public class AccountServiceImpl implements IAccountService{

    public AccountServiceImpl(AppRoleRepository roleRepository, CustumizedUserDao gestionnaireDAO,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		super();
		this.roleRepository = roleRepository;
		this.gestionnaireDAO = gestionnaireDAO;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	private AppRoleRepository roleRepository;
    private CustumizedUserDao gestionnaireDAO;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
	@Override
	public AppRole saveRole(AppRole role) {
		return roleRepository.save(role);
	}
	
	@Override
	public CustomUser loadUserByLogin(String login) {
		return gestionnaireDAO.findByUsername(login);
	}
	@Override
	public void AddRoleToGestionnaire(String login, String roleName) {
		CustomUser gestionnaires = loadUserByLogin(login);
        AppRole appRole = roleRepository.findByRoleName(roleName);
        gestionnaires.getRoles().add(appRole);
		
	}

	@Override
	public void AddNewUser(CustomUser user) {
		gestionnaireDAO.save(user);
		
	}

	@Override
	public AppRole findRole(String CustomUser) {
		return roleRepository.findByRoleName(CustomUser);
	}




}