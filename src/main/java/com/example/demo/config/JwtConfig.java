package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.filters.JWTAthentificationFilter;
import com.example.demo.filters.JWTAuthorizationFilter;
import com.example.demo.service.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class JwtConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private BCryptPasswordEncoder bycript;
	
	 @Autowired
	 private UserDetailsServiceImpl customUserDetailService;
	 
	//here we say how we want to manage our authentication process
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(auth);
		 auth.userDetailsService(customUserDetailService).passwordEncoder(bycript);
		
	}
	
	
	//with this method we will control witch end points are permitted and witch are not permitted
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//super.configure(http);
		/*
		http
	        .csrf()
	        .disable()
			.cors()
	        .disable()
	        .authorizeRequests()
	        .antMatchers("/api/generateToken").permitAll()//only allow this endpoint without authentication
	        .anyRequest().authenticated()//for any other request, authentication should performed
	        .and()
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//every request should be independent of other and server does not have to manage session
		 */
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests()
        .antMatchers("/login").permitAll();
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(new JWTAthentificationFilter(authenticationManager()));
		http.addFilterBefore(new JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }


    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
    
    
    /*
             http.authorizeRequests().antMatchers("/SearchGestionnaires","/UpdateGest/**","/AddGest","/ModifyDir/**").hasAuthority("ADMIN");
     
     */
    
    //*********************************************************************************************************************************************
    
    /*
     
        http.authorizeRequests().antMatchers("/Examinators")
                .access("hasAuthority('ADMIN') or hasAuthority('GESTLV1') or hasAuthority('GESTLV2')");
    */
}
