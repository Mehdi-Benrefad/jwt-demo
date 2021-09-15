package com.example.demo;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.demo.entities.AppRole;
import com.example.demo.entities.CustomUser;
import com.example.demo.service.IAccountService;

@SpringBootApplication
public class JwtApiApplication {

	@Autowired
	private IAccountService accountService;
	public static void main(String[] args) {
		SpringApplication.run(JwtApiApplication.class, args);
	}

	@Bean
	BCryptPasswordEncoder getBCR(){
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	CommandLineRunner begin(IAccountService accountService){
		return args -> {
			Stream.of("benrefadmehdi@gmail.com","admin@gmail.com").forEach(r->{
				CustomUser gest = new CustomUser();
				gest.setUsername(r);
				gest.setPassword(getBCR().encode("123456789"));
				accountService.AddNewUser(gest);
			});
			
			accountService.saveRole(new AppRole(null,"admin"));
			accountService.saveRole(new AppRole(null,"user"));
			accountService.AddRoleToGestionnaire("benrefadmehdi@gmail.com","admin");
			accountService.AddRoleToGestionnaire("admin@gmail.com","user");
			
		};
	}
}
