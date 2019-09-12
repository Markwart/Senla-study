package by.senla.study.controller;

import java.util.Date;

import by.senla.study.api.service.IUserAccountService;
import by.senla.study.dao.search.HibernateSearchInitializer;
import by.senla.study.dao.utils.HibernateEntityManagerUtil;
import by.senla.study.model.entity.UserAccount;
import by.senla.study.service.impl.UserAccountService;

public class Init {

	public static void main(String[] args) throws InterruptedException {
		
		HibernateSearchInitializer.initIndex();
		
		System.out.println("Hello! This is application for working with the database \n");

		IUserAccountService service = UserAccountService.getInstance();
		
		UserAccount user1 = service.createEntity();
		user1.setName("Mark");
		user1.setEmail("mmm@gmail.com");
		user1.setCreated(new Date());
		user1.setUpdated(new Date());
		service.insert(user1);

		System.out.println("SIZE=" + service.selectAll().size());
		
		service.exportToCSV();
		
		HibernateEntityManagerUtil.close();
	}

}
