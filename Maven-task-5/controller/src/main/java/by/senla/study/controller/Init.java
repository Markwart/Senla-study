package by.senla.study.controller;


import java.util.Date;
import java.util.Scanner;

import by.senla.study.api.service.IUserAccountService;
import by.senla.study.model.entity.UserAccount;
import by.senla.study.service.impl.UserAccountService;

public class Init {

	public static void main(String[] args) {
		

		Scanner scanner = new Scanner(System.in);

		System.out.println("Hello! This is application for working with the database \n");
		
		IUserAccountService service = UserAccountService.getInstance();
		
		UserAccount user1 = service.createEntity();
		user1.setName("Mark");
		user1.setEmail("mark@gmail.com");
		user1.setCreated(new Date());
		user1.setUpdated(new Date());
		service.insert(user1);
		
		UserAccount user2 = service.createEntity();
		user2.setName("David");
		user2.setEmail("david@gmail.com");
		user2.setCreated(new Date());
		user2.setUpdated(new Date());
		service.insert(user2);
		
		System.out.println(service.get(1).getEmail());
		
		scanner.close();
	}

}
