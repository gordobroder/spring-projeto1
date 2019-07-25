package com.example.demo.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.example.demo.entity.*;
import com.example.demo.repository.UserRepository;

@Component
public class DataInitilizr implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	UserRepository userRepository;
		
	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
		
		List<User> users = userRepository.findAll();
		
		if (users.isEmpty()) {
			
			createUser("Gustavo Schmidt", "gustavo@gmail.com");
			createUser("Mario", "mario@gmail.com");
			
		}
		
		User user = userRepository.findByNameIgnoreCase("mario");

		System.out.println(user.getName());
	
	}	
	
	public void createUser(String name, String email) {
		
		User user = new User(name, email);
		
		userRepository.save(user);
		
	}

}
