package by.senla.study.controller;

import java.util.Date;

import by.senla.study.api.service.IUserAccountService;
import by.senla.study.dao.utils.HibernateEntityManagerUtil;
import by.senla.study.model.entity.UserAccount;
import by.senla.study.service.impl.UserAccountService;

public class Init {

	public static void main(String[] args) {

		System.out.println("Hello! This is application for working with the database \n");

		IUserAccountService service = UserAccountService.getInstance();
		
		UserAccount user1 = service.createEntity();
		user1.setName("MIKOLA");
		user1.setEmail("MIKOLA@gmail.com");
		user1.setCreated(new Date());
		user1.setUpdated(new Date());
		service.insert(user1);

		System.out.println("LIST=" + service.selectAll().size());
		System.out.println("EMAIL=" + service.get(10).getEmail());
		
		HibernateEntityManagerUtil.close();
	}

}
