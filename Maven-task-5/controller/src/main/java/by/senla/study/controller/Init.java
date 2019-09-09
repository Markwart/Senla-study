package by.senla.study.controller;

import by.senla.study.api.service.IUserAccountService;
import by.senla.study.dao.utils.HibernateEntityManagerUtil;
import by.senla.study.service.impl.UserAccountService;

public class Init {

	public static void main(String[] args) {

		System.out.println("Hello! This is application for working with the database \n");

		IUserAccountService service = UserAccountService.getInstance();

		System.out.println("EMAIL=" + service.get(4).getEmail());
		System.out.println("LIST=" + service.selectAll().size());

		HibernateEntityManagerUtil.close();
	}

}
