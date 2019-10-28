package by.senla.study.project.init;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Common {

	private static final Logger LOGGER = Logger.getLogger(Common.class.getName());

	public static void chooseOperation(Scanner scanner) {

		System.out.println("What operation do you want to perform? \n");
		StringBuilder operations = new StringBuilder("1. Insert record to database \n")
				.append("2. Delete record from database \n").append("3. Update record in database \n")
				.append("4. Get record by \"pk\" from database \n").append("5. Get record list from database \n")
				.append("6. Find records with some condition \n").append("7. Exit \n");
		System.out.println(operations);

		int operationNumber = 0;
		try {
			operationNumber = scanner.nextInt();

			switch (operationNumber) {
			case 1:
				insertRecord(scanner);
				break;
			case 2:
				deleteRecord(scanner);
				break;
			case 3:
				updateRecord(scanner);
				break;
			case 4:
				getRecord(scanner);
				break;
			case 5:
				getRecordList(scanner);
				break;
			case 6:
				findRecordWithCondition(scanner);
				break;
			case 7:
				System.out.println("Goodbye!");
				break;
			default:
				System.out.println("Incorrectly entered operation number! Try again.\n");
				chooseOperation(scanner);
				break;
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Incorrectly entered operation number!", e);
			chooseOperation(scanner);
		}
	}

	public static void continueQuestion(Scanner scanner) {
		System.out.println("Do you want to continue?");
		System.out.println("1. Yes / 2. No");
		int choise = scanner.nextInt();
		try {
			switch (choise) {
			case 1:
				chooseOperation(scanner);
				break;
			case 2:
				System.out.println("Goodbye!");
				break;
			default:
				System.out.println("Incorrectly entered number! Try again.\n");
				continueQuestion(scanner);
				break;
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Incorrectly entered number!", e);
			continueQuestion(scanner);
		}
	}

	public static int chooseTable(Scanner scanner) {
		System.out.println("Select the table you want to perform the operation on \n");
		StringBuilder chooseTable = new StringBuilder("1. Product \n").append("2. PC \n").append("3. Laptop \n")
				.append("4. Printer \n");
		System.out.println(chooseTable);

		int tableNumber = 0;
		try {
			tableNumber = scanner.nextInt();
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Incorrectly entered table number!", e);
			chooseTable(scanner);
		}
		return tableNumber;
	}

	private static void insertRecord(Scanner scanner) {
		int tableNumber = chooseTable(scanner);
		try {
			switch (tableNumber) {
			case 1:
				ProductOperation.enterProductData(scanner);
				break;
			case 2:
				PCOperation.enterPCData(scanner);
				break;
			case 3:
				LaptopOperation.enterLaptopData(scanner);
				break;
			case 4:
				PrinterOperation.enterPrinterData(scanner);
				break;
			default:
				System.out.println("Incorrectly entered table number! Try again.\n");
				chooseTable(scanner);
				break;
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Incorrectly entered table number!", e);
			chooseTable(scanner);
		}
	}

	private static void deleteRecord(Scanner scanner) {
		int tableNumber = chooseTable(scanner);
		try {
			switch (tableNumber) {
			case 1:
				ProductOperation.deleteProductRecord(scanner);
				break;
			case 2:
				PCOperation.deletePCRecord(scanner);
				break;
			case 3:
				LaptopOperation.deleteLaptopRecord(scanner);
				break;
			case 4:
				PrinterOperation.deletePrinterRecord(scanner);
				break;
			default:
				System.out.println("Incorrectly entered table number! Try again.\n");
				chooseTable(scanner);
				break;
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Incorrectly entered table number!", e);
			chooseTable(scanner);
		}
	}

	private static void updateRecord(Scanner scanner) {
		int tableNumber = chooseTable(scanner);
		try {
			switch (tableNumber) {
			case 1:
				ProductOperation.updateProductRecord(scanner);
				break;
			case 2:
				PCOperation.updatePCRecord(scanner);
				break;
			case 3:
				LaptopOperation.updateLaptopRecord(scanner);
				break;
			case 4:
				PrinterOperation.updatePrinterRecord(scanner);
				break;
			default:
				System.out.println("Incorrectly entered table number! Try again.\n");
				chooseTable(scanner);
				break;
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Incorrectly entered table number!", e);
			chooseTable(scanner);
		}
	}

	private static void getRecord(Scanner scanner) {
		int tableNumber = chooseTable(scanner);
		try {
			switch (tableNumber) {
			case 1:
				ProductOperation.getProductRecord(scanner);
				break;
			case 2:
				PCOperation.getPCRecord(scanner);
				break;
			case 3:
				LaptopOperation.getLaptopRecord(scanner);
				break;
			case 4:
				PrinterOperation.getPrinterRecord(scanner);
				break;
			default:
				System.out.println("Incorrectly entered table number! Try again.\n");
				chooseTable(scanner);
				break;
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Incorrectly entered table number!", e);
			chooseTable(scanner);
		}
	}

	private static void getRecordList(Scanner scanner) {
		int tableNumber = chooseTable(scanner);
		try {
			switch (tableNumber) {
			case 1:
				ProductOperation.getProductRecordList(scanner);
				break;
			case 2:
				PCOperation.getPCRecordList(scanner);
				break;
			case 3:
				LaptopOperation.getLaptopRecordList(scanner);
				break;
			case 4:
				PrinterOperation.getPrinterRecordList(scanner);
				break;
			default:
				System.out.println("Incorrectly entered table number! Try again.\n");
				chooseTable(scanner);
				break;
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Incorrectly entered table number!", e);
			chooseTable(scanner);
		}
	}

	private static void findRecordWithCondition(Scanner scanner) {
		StringBuilder operations = new StringBuilder("1. Find all PC worth less than 500 \n").append(
				"2. Find makers that would produce both PC with a speed not less than 750 MHz and notebooks with a speed not less than than 750 MHz. \n")
				.append("3. For each speed value, find the average cost of a PC with the same processor speed \n")
				.append("4. Find printer makers \n");
		System.out.println(operations);
		int operationNumber = scanner.nextInt();
		try {
			switch (operationNumber) {
			case 1:
				PCOperation.findPCWithCost();
				break;
			case 2:
				ProductOperation.findPCAndLaptopMakers();
				break;
			case 3:
				PCOperation.findAverageCostPc();
				break;
			case 4:
				ProductOperation.findPrinterMakers();
				break;
			default:
				System.out.println("Incorrectly entered operation number! Try again.\n");
				findRecordWithCondition(scanner);
				break;
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Incorrectly entered operation number!", e);
		} finally {
			System.out.println();
			continueQuestion(scanner);
		}
	}
}
