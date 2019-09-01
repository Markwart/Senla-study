package by.senla.study.project.init;

import java.util.Scanner;

import by.senla.study.project.service.impl.PrinterServiceImpl;

public class PrinterOperation {

	public static void deletePrinterRecord(Scanner scanner) {
		System.out.println("Enter \"pk\"(id) \n");
		try {
			int id = scanner.nextInt();
			PrinterServiceImpl dao = new PrinterServiceImpl();
			dao.delete(id);
			Common.chooseOperation(scanner);
		} catch (Exception e) {
			System.out.println("Incorrectly entered id number! Try again.\n");
			Common.chooseOperation(scanner);
		}
	}

	public static void getPrinterRecord(Scanner scanner) {
		System.out.println("Enter \"pk\"(id)");
		try {
			int id = scanner.nextInt();
			PrinterServiceImpl dao = new PrinterServiceImpl();
			dao.get(id);
			
			System.out.println(" ");
			
			Common.chooseOperation(scanner);
		} catch (Exception e) {
			System.out.println("Incorrectly entered id number! Try again.\n");
			Common.chooseOperation(scanner);
		}
	}

	public static void enterPrinterData(Scanner scanner) {
		System.out.println("Please, enter the data of printer:");
		System.out.println("model = ");
		System.out.println("color('y' or 'n') = ");
		System.out.println("type = ");
		System.out.println("price = ");
	}

	public static void getPrinterRecordList(Scanner scanner) {
		PrinterServiceImpl dao = new PrinterServiceImpl();
		dao.getAll();
		
		System.out.println(" ");
		
		Common.chooseOperation(scanner);
	}

	public static void updatePrinterRecord(Scanner scanner) {
		// TODO Auto-generated method stub
		
	}
}
