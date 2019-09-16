package by.senla.study.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.senla.study.api.service.IAdService;
import by.senla.study.api.service.IUserAccountService;

public class Init {

	public static void main(String[] args) throws InterruptedException {

		ApplicationContext context = new ClassPathXmlApplicationContext("service-context.xml");
		IUserAccountService userAccountService = context.getBean(IUserAccountService.class);
		IAdService adService = context.getBean(IAdService.class);

		System.out.println("Hello! This is application for working with the database \n");

		System.out.println("SIZE=" + userAccountService.selectAll().size());
		System.out.println("EMAIL=" + userAccountService.getByID(33).getEmail());
		System.out.println("TEXT=" + adService.getByID(1).getText());
		System.out.println(adService.searchByIndex("thing").get(0).getTheme());

		userAccountService.exportToCSV();

	}
}
