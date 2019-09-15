package by.senla.study.controller;

import by.senla.study.api.service.IAdService;
import by.senla.study.api.service.IUserAccountService;
import by.senla.study.dao.search.HibernateSearchInitializer;
import by.senla.study.dao.utils.HibernateEntityManagerUtil;
import by.senla.study.service.impl.AdService;
import by.senla.study.service.impl.UserAccountService;

public class Init {

	public static void main(String[] args) throws InterruptedException {
		
		HibernateSearchInitializer.initIndex();
		
		System.out.println("Hello! This is application for working with the database \n");

		IUserAccountService userAccountService = UserAccountService.getInstance();
		IAdService adService = AdService.getInstance();
		
		System.out.println("SIZE=" + userAccountService.selectAll().size());
		System.out.println(adService.searchByIndex("thing").get(0).getTheme());
		System.out.println("EMAIL=" + userAccountService.getByID(33).getEmail());
		
		//userAccountService.exportToCSV();
		
		HibernateEntityManagerUtil.close();
	}

}
