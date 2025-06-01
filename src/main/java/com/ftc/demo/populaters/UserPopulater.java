package com.ftc.demo.populaters;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ftc.demo.entities.Company;
import com.ftc.demo.entities.Roles;
import com.ftc.demo.entities.User;
import com.ftc.demo.repositories.RolesRepository;
import com.ftc.demo.repositories.UserRepository;
import com.ftc.demo.security.BCryptService;

@Component
public class UserPopulater {

	private final UserRepository userRepository;
	private final RolesRepository rolesRepository;
	private final BCryptService bCryptService;
	private List<User> sellers = new ArrayList<>();

	public UserPopulater(UserRepository userRepository, RolesRepository rolesRepository, BCryptService bCryptService) {
		super();
		this.userRepository = userRepository;
		this.rolesRepository = rolesRepository;
		this.bCryptService = bCryptService;
	}

	public List<User> populate(List<Roles> roles) {
		HashSet<Roles> rolesSet = new HashSet<>();
		rolesSet.add(roles.get(1));
		List<User> customers = List.of(new User("john.doe@example.com", "johndoe", bCryptService.hashPassword("password123"), "111-111-1111", 2000),
				new User("jane.smith@example.com", "janesmith", bCryptService.hashPassword("secure456"), "222-222-2222", 2000),
				new User("michael.lee@example.com", "mikelee", bCryptService.hashPassword("pass789"), "333-333-3333", 500),
				new User("sarah.jones@example.com", "sarahj", bCryptService.hashPassword("mypassword"), "444-444-4444",  400),
				new User("daniel.kim@example.com", "danielk", bCryptService.hashPassword("abc123"), "555-555-5555", 200));
		for (User user : customers) {
			user.setRoles(rolesSet);
		}
		userRepository.saveAll(customers);
		
		rolesSet.add(roles.get(2));
		
		List<User> sellers = List.of(new User("lisa.wilson@example.com", "lisaw", bCryptService.hashPassword("qwerty123"), "666-666-6666", 1000),
				new User("tom.brown@example.com", "tomb", bCryptService.hashPassword("letmein789"), "777-777-7777", 1000),
				new User("emily.clark@example.com", "emilyc", bCryptService.hashPassword("password321"), "888-888-8888", 1000));
		for (User user : sellers) {
			user.setRoles(rolesSet);
		}
		this.sellers = sellers;
		List<User> saveAll = userRepository.saveAll(sellers);
		
		
		rolesSet.add(roles.get(0));
		List<User> admins = List.of(new User("kevin.martin@example.com", "kevinm", bCryptService.hashPassword("passpass"), "999-999-9999", 1000),
				new User("olivia.taylor@example.com", "oliviat", bCryptService.hashPassword("olivia123"), "000-000-0000", 1000));
		admins = admins.stream()
			.map(user -> {
				user.setRoles(rolesSet);
					return user;
				})
			.toList();
		userRepository.saveAll(admins);
		
		return saveAll;
		
	}
	
	public void repopulate(List<Company> companies) {
		for (int i = 0; i < sellers.size(); i++) {
			User user = sellers.get(i);
			user.setCompany(companies.get(i));
			userRepository.save(user);
			
			
		}
		
	}

}
