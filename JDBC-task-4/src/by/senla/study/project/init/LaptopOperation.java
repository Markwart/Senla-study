package by.senla.study.project.init;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import by.senla.study.project.dao.jdbc.impl.entity.Laptop;
import by.senla.study.project.service.ILaptopService;
import by.senla.study.project.service.impl.LaptopService;

public class LaptopOperation {

	private static final Logger LOGGER = Logger.getLogger(LaptopOperation.class.getName());
	private static ILaptopService service = LaptopService.getInstance();

	public static void deleteLaptopRecord(Scanner scanner) {
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

	public static void getLaptopRecord(Scanner scanner) {
		System.out.println("Enter \"pk\"(id) \n");
		try {
			int id = scanner.nextInt();
			Laptop entity = service.get(id);

			StringBuilder laptop = new StringBuilder().append(entity.getModel().getModel()).append(" ")
					.append(entity.getHd()).append(" ").append(entity.getRam()).append(" ").append(entity.getSpeed())
					.append(" ").append(entity.getScreen()).append(" ").append(entity.getPrice());
			System.out.println(laptop);

		} catch (Exception e) {
			System.out.println("Incorrectly entered id number! Try again.\n");
			LOGGER.log(Level.SEVERE, "Failed to get record", e);
		} finally {
			Common.chooseOperation(scanner);
		}
	}

	public static void enterLaptopData(Scanner scanner) {

		Map<String, String> data = new HashMap<String, String>();

		System.out.println("Please, enter the data of laptop: \n");
		try {
			System.out.println("model: ");
			String model = scanner.next();
			System.out.println("speed: ");
			String speed = scanner.next();
			System.out.println("ram: ");
			String ram = scanner.next();
			System.out.println("hd: ");
			String hd = scanner.next();
			System.out.println("screen: ");
			String screen = scanner.next();
			System.out.println("price: ");
			String price = scanner.next();

			data.put("model", model);
			data.put("speed", speed);
			data.put("ram", ram);
			data.put("hd", hd);
			data.put("screen", screen);
			data.put("price", price);
			service.save(data);

		} catch (Exception e) {
			System.out.println("Incorrectly entered data! Try again.\n");
			LOGGER.log(Level.SEVERE, "Failed to save data", e);
		} finally {
			Common.chooseOperation(scanner);
		}
	}

	public static void getLaptopRecordList(Scanner scanner) {
		List<Laptop> laptopList = service.getAll();

		System.out.println(laptopList.size());

		Common.chooseOperation(scanner);
	}

	public static void updateLaptopRecord(Scanner scanner) {

		Map<String, String> data = new HashMap<String, String>();

		System.out.println("Please, enter the data of laptop you want to change: \n");
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
			System.out.println("screen: ");
			String screen = scanner.next();
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
			if (screen != null) {
				data.put("screen", screen);
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
