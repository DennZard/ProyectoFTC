package com.ftc.demo.populaters;

import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Component;

import com.ftc.demo.entities.Roles;
import com.ftc.demo.entities.User;
import com.ftc.demo.repositories.UserRepository;

@Component
public class UserPopulater {

	private final UserRepository userRepository;

	public UserPopulater(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public List<User> populate(List<Roles> roles) {
		HashSet<Roles> rolesSet = new HashSet<>();
		rolesSet.add(roles.get(2));
		List<User> customers = List.of(new User("john.doe@example.com", "johndoe", "password123", "111-111-1111"),
				new User("jane.smith@example.com", "janesmith", "secure456", "222-222-2222"),
				new User("michael.lee@example.com", "mikelee", "pass789", "333-333-3333"),
				new User("sarah.jones@example.com", "sarahj", "mypassword", "444-444-4444"),
				new User("daniel.kim@example.com", "danielk", "abc123", "555-555-5555"));
		for (User user : customers) {
			user.setRoles(rolesSet);
		}
		
		userRepository.saveAll(customers);

		rolesSet.add(roles.get(1));

		List<User> sellers = List.of(new User("lisa.wilson@example.com", "lisaw", "qwerty123", "666-666-6666"),
				new User("tom.brown@example.com", "tomb", "letmein789", "777-777-7777"),
				new User("emily.clark@example.com", "emilyc", "password321", "888-888-8888"));
		for (User user : sellers) {
			user.setRoles(rolesSet);
		}
		userRepository.saveAll(sellers);

		rolesSet.add(roles.get(0));
		List<User> admins = List.of(new User("kevin.martin@example.com", "kevinm", "passpass", "999-999-9999"),
				new User("olivia.taylor@example.com", "oliviat", "olivia123", "000-000-0000"));
		for (User user : admins) {
			user.setRoles(rolesSet);
		}
		
		userRepository.saveAll(admins);
		
		return sellers;
	}

}
