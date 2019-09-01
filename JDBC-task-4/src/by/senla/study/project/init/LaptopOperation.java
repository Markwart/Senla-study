package by.senla.study.project.init;

import java.util.Scanner;

import by.senla.study.project.service.impl.LaptopServiceImpl;

public class LaptopOperation {

	public static void deleteLaptopRecord(Scanner scanner) {
		System.out.println("Enter \"pk\"(id) \n");
		try {
			int id = scanner.nextInt();
			LaptopServiceImpl dao = new LaptopServiceImpl();
			dao.delete(id);
			Common.chooseOperation(scanner);
		} catch (Exception e) {
			System.out.println("Incorrectly entered id number! Try again.\n");
			Common.chooseOperation(scanner);
		}
	}
	
	public static void getLaptopRecord(Scanner scanner) {
		System.out.println("Enter \"pk\"(id) \n");
		try {
			int id = scanner.nextInt();
			LaptopServiceImpl dao = new LaptopServiceImpl();
			dao.get(id);
			
			System.out.println(" ");
			
			Common.chooseOperation(scanner);
		} catch (Exception e) {
			System.out.println("Incorrectly entered id number! Try again.\n");
			Common.chooseOperation(scanner);
		}
	}
	
	public static void enterLaptopData(Scanner scanner) {
		System.out.println("Please, enter the data of laptop:");
		System.out.println("model = ");
		System.out.println("speed = ");
		System.out.println("ram = ");
		System.out.println("hd = ");
		System.out.println("screen = ");
		System.out.println("price = ");
	}

	public static void getLaptopRecordList(Scanner scanner) {
		LaptopServiceImpl dao = new LaptopServiceImpl();
		dao.getAll();
		
		System.out.println(" ");
		
		Common.chooseOperation(scanner);
	}

	public static void updateLaptopRecord(Scanner scanner) {
		// TODO Auto-generated method stub
		
	}
}
