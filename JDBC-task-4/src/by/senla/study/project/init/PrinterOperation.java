package by.senla.study.project.init;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import by.senla.study.project.dao.jdbc.impl.entity.Printer;
import by.senla.study.project.service.impl.PrinterServiceImpl;

public class PrinterOperation {

	private static final Logger LOGGER = Logger.getLogger(PrinterOperation.class.getName());
	static PrinterServiceImpl service = PrinterServiceImpl.getInstance();

	public static void deletePrinterRecord(Scanner scanner) {
		System.out.println("Enter \"pk\"(id) \n");
		try {
			int id = scanner.nextInt();
			service.delete(id);
		} catch (Exception e) {
			System.out.println("Incorrectly entered id number! Try again.\n");
			LOGGER.log(Level.SEVERE, "Failed to delete record", e);
		} finally {
			Common.chooseOperation(scanner);
		}
	}

	public static void getPrinterRecord(Scanner scanner) {
		System.out.println("Enter \"pk\"(id) \n");
		try {
			int id = scanner.nextInt();
			Printer entity = service.get(id);

			StringBuilder printer = new StringBuilder().append(entity.getModel().getModel()).append(" ")
					.append(entity.getType()).append(" ").append(entity.getColor()).append(" ")
					.append(entity.getPrice());
			System.out.println(printer);

		} catch (Exception e) {
			System.out.println("Incorrectly entered id number! Try again.\n");
			LOGGER.log(Level.SEVERE, "Failed to get record", e);
		} finally {
			Common.chooseOperation(scanner);
		}
	}

	public static void enterPrinterData(Scanner scanner) {

		Map<String, String> data = new HashMap<String, String>();

		System.out.println("Please, enter the data of printer: \n");
		try {
			System.out.println("model: ");
			String model = scanner.next();
			System.out.println("color('y' or 'n'): ");
			String color = scanner.next();
			System.out.println("type: ");
			String type = scanner.next();
			System.out.println("price: ");
			String price = scanner.next();

			data.put("model", model);
			data.put("color", color);
			data.put("type", type);
			data.put("price", price);
			service.save(data);

		} catch (Exception e) {
			System.out.println("Incorrectly entered data! Try again.\n");
			LOGGER.log(Level.SEVERE, "Failed to save data", e);
		} finally {
			Common.chooseOperation(scanner);
		}
	}

	public static void getPrinterRecordList(Scanner scanner) {
		List<Printer> printerList = service.getAll();

		System.out.println(printerList.size());

		Common.chooseOperation(scanner);
	}

	public static void updatePrinterRecord(Scanner scanner) {

		Map<String, String> data = new HashMap<String, String>();

		System.out.println("Please, enter the data of printer you want to change: \n");
		try {
			System.out.println("Enter \"pk\"(id) \n");
			int id = scanner.nextInt();
			System.out.println("model: ");
			String model = scanner.next();
			System.out.println("color('y' or 'n'): ");
			String color = scanner.next();
			System.out.println("type: ");
			String type = scanner.next();
			System.out.println("price: ");
			String price = scanner.next();

			if (model != null) {
				data.put("model", model);
			}
			if (color != null) {
				data.put("color", color);
			}
			if (type != null) {
				data.put("type", type);
			}
			if (price != null) {
				data.put("price", price);
			}
			service.update(data, id);

		} catch (Exception e) {
			System.out.println("Incorrectly entered data or id! Try again.\n");
			LOGGER.log(Level.SEVERE, "Failed to update record", e);
		} finally {
			Common.chooseOperation(scanner);
		}
	}
}
