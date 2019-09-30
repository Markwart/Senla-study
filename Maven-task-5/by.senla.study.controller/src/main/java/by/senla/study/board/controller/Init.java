package by.senla.study.board.controller;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.senla.study.board.api.service.IAdService;
import by.senla.study.board.api.service.IUserAccountService;
import by.senla.study.board.model.entity.Ad;

public class Init {

	public static void main(String[] args) throws InterruptedException {

		ApplicationContext context = new ClassPathXmlApplicationContext("service-context.xml");
		IUserAccountService userAccountService = context.getBean(IUserAccountService.class);
		IAdService adService = context.getBean(IAdService.class);

		System.out.println("SIZE=" + userAccountService.selectAll().size());
		System.out.println("EMAIL=" + userAccountService.getByID(33).getEmail());
		System.out.println("CATEGORY=" + adService.getFullInfo(1).getCategory().getName());

		List<Ad> listSearch = adService.searchByIndex("thing");
		for (Ad ad : listSearch) {
			System.out.println("SEARCH_ID=" + ad.getId());
		}

		List<Ad> list = adService.findAdsByCategory("old", null, true);
		for (Ad ad : list) {
			System.out.println("STATUS = " + ad.getStatus() + " ID=" + ad.getId());
		}

		userAccountService.exportToCSV();
		
	}
}
