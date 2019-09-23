package by.senla.study.controller;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.senla.study.api.service.IAdService;
import by.senla.study.api.service.IUserAccountService;
import by.senla.study.model.entity.Ad;

public class Init {

	public static void main(String[] args) throws InterruptedException {

		ApplicationContext context = new ClassPathXmlApplicationContext("service-context.xml");
		IUserAccountService userAccountService = context.getBean(IUserAccountService.class);
		IAdService adService = context.getBean(IAdService.class);

		System.out.println("SIZE=" + userAccountService.selectAll().size());
		System.out.println("EMAIL=" + userAccountService.getByID(33).getEmail());
		System.out.println("TEXT=" + adService.getByID(1).getText());
		System.out.println("SEARCH=" + adService.searchByIndex("thing").get(0).getTheme());
		
		
		List<Ad> list = adService.findAdsByCategory("old", null, true);
		for (Ad ad : list) {
			System.out.println("STATUS = " + ad.getStatus() + " ID=" + ad.getId());
		}
		
		userAccountService.exportToCSV();

	}
}
