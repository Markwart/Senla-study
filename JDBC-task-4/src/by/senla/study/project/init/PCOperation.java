package by.senla.study.project.init;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import by.senla.study.project.dao.jdbc.impl.entity.PC;
import by.senla.study.project.service.impl.PCServiceImpl;

public class PCOperation {

	private static final Logger LOGGER = Logger.getLogger(PCOperation.class.getName());
	static PCServiceImpl service = PCServiceImpl.getInstance();

	public static void deletePCRecord(Scanner scanner) {
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

	public static void getPCRecord(Scanner scanner) {
		System.out.println("Enter \"pk\"(id) \n");
		try {
			int id = scanner.nextInt();
			PC entity = service.get(id);

			StringBuilder pc = new StringBuilder().append(entity.getModel().getModel()).append(" ")
					.append(entity.getHd()).append(" ").append(entity.getRam()).append(" ").append(entity.getSpeed())
					.append(" ").append(entity.getCd()).append(" ").append(entity.getPrice());
			System.out.println(pc);

		} catch (Exception e) {
			System.out.println("Incorrectly entered id number! Try again.\n");
			LOGGER.log(Level.SEVERE, "Failed to get record", e);
		} finally {
			Common.chooseOperation(scanner);
		}
	}

	public static void enterPCData(Scanner scanner) {

		Map<String, String> data = new HashMap<String, String>();

		System.out.println("Please, enter the data of PC: \n");
		try {
			System.out.println("model: ");
			String model = scanner.next();
			System.out.println("speed: ");
			String speed = scanner.next();
			System.out.println("ram: ");
			String ram = scanner.next();
			System.out.println("hd: ");
			String hd = scanner.next();
			System.out.println("cd: ");
			String cd = scanner.next();
			System.out.println("price: ");
			String price = scanner.next();

			data.put("model", model);
			data.put("speed", speed);
			data.put("ram", ram);
			data.put("hd", hd);
			data.put("cd", cd);
			data.put("price", price);
			service.save(data);

		} catch (Exception e) {
			System.out.println("Incorrectly entered data! Try again.\n");
			LOGGER.log(Level.SEVERE, "Failed to save data", e);
		} finally {
			Common.chooseOperation(scanner);
		}
	}

	public static void getPCRecordList(Scanner scanner) {
		List<PC> pcList = service.getAll();

		System.out.println(pcList.size());

		Common.chooseOperation(scanner);
	}

	public static void updatePCRecord(Scanner scanner) {

		Map<String, String> data = new HashMap<String, String>();

		System.out.println("Please, enter the data of pc you want to change: \n");
		try {
			System.out.println("Enter \"pk\"(id) \n");
			int id = scanner.nextInt();
			System.out.println("model: ");
			String model = scanner.next();
			System.out.println("speed: ");
			String speed = scanner.next();
			System.out.println("ram: ");
			String ram = scanner.next();
			System.out.println("hd: ");
			String hd = scanner.next();
			System.out.println("cd: ");
			String cd = scanner.next();
			System.out.println("price: ");
			String price = scanner.next();

			if (model != null) {
				data.put("model", model);
			}
			if (speed != null) {
				data.put("speed", speed);
			}
			if (ram != null) {
				data.put("ram", ram);
			}
			if (hd != null) {
				data.put("hd", hd);
			}
			if (cd != null) {
				data.put("cd", cd);
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
