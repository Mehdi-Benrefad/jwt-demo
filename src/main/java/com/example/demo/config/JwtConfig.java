package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.example.demo.service.CustomUserDetailService;

@Configuration
@EnableWebSecurity
public class JwtConfig extends WebSecurityConfigurerAdapter{
	
	 @Autowired
	 private CustomUserDetailService customUserDetailService;
	 
	//here we say how we want to manage our authentication process
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(auth);
		 auth.userDetailsService(customUserDetailService);
		
	}
	
	
	//with this method we will control witch end points are permitted and witch are not permitted
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		super.configure(http);
	}

}
