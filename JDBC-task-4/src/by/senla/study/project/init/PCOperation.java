package by.senla.study.project.init;

import java.util.Scanner;

import by.senla.study.project.service.impl.PCServiceImpl;

public class PCOperation {

	public static void deletePCRecord(Scanner scanner) {
		System.out.println("Enter \"pk\"(id) \n");
		try {
			int id = scanner.nextInt();
			PCServiceImpl dao = new PCServiceImpl();
			dao.delete(id);
			Common.chooseOperation(scanner);
		} catch (Exception e) {
			System.out.println("Incorrectly entered id number! Try again.\n");
			Common.chooseOperation(scanner);
		}
	}

	public static void getPCRecord(Scanner scanner) {
		System.out.println("Enter \"pk\"(id) \n");
		try {
			int id = scanner.nextInt();
			PCServiceImpl dao = new PCServiceImpl();
			dao.get(id);

			System.out.println(" ");

			Common.chooseOperation(scanner);
		} catch (Exception e) {
			System.out.println("Incorrectly entered id number! Try again.\n");
			Common.chooseOperation(scanner);
		}
	}

	public static void enterPCData(Scanner scanner) {
		System.out.println("Please, enter the data of PC:");
		System.out.println("model = ");
		System.out.println("speed = ");
		System.out.println("ram = ");
		System.out.println("hd = ");
		System.out.println("cd = ");
		System.out.println("price = ");
	}

	public static void getPCRecordList(Scanner scanner) {
		PCServiceImpl dao = new PCServiceImpl();
		dao.getAll();

		System.out.println(" ");

		Common.chooseOperation(scanner);
	}

	public static void updatePCRecord(Scanner scanner) {
		// TODO Auto-generated method stub
		
	}
}
