package com.example.demo.service;

import com.example.demo.entities.AppRole;
import com.example.demo.entities.CustomUser;

public interface IAccountService {
    public AppRole saveRole(AppRole role);
    public CustomUser loadUserByLogin(String login);
    public void AddRoleToGestionnaire(String login,String roleName);
    public void AddNewUser(CustomUser user);
    public AppRole findRole(String CustomUser);
}