package by.senla.study.board.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import by.senla.study.board.api.service.IAdService;
import by.senla.study.board.api.service.ICategoryService;
import by.senla.study.board.api.service.IPersonalDataService;
import by.senla.study.board.api.service.IUserAccountService;

public class Init {

	public static void main(String[] args) throws InterruptedException {

		ApplicationContext context = new ClassPathXmlApplicationContext("service-context.xml");
		IUserAccountService userAccountService = context.getBean(IUserAccountService.class);
		IAdService adService = context.getBean(IAdService.class);
		IPersonalDataService personalDataService = context.getBean(IPersonalDataService.class);
		ICategoryService categoryService = context.getBean(ICategoryService.class);
		
		System.out.println(categoryService.getFullInfo(1).getAds().size());
	}
}
