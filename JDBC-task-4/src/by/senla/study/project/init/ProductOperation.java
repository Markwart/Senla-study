package by.senla.study.project.init;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import by.senla.study.project.service.impl.ProductServiceImpl;

public class ProductOperation {

	public static void deleteProductRecord(Scanner scanner) {
		System.out.println("Enter \"pk\"(model) \n");
		try {
			String model = scanner.nextLine();
			ProductServiceImpl dao = new ProductServiceImpl();
			dao.delete(model);
			Common.chooseOperation(scanner);
		} catch (Exception e) {
			System.out.println("Incorrectly entered model! Try again.\n");
			Common.chooseOperation(scanner);
		}
	}

	public static void getProductRecord(Scanner scanner) {
		System.out.println("Enter \"pk\"(model) \n");
		try {
			String model = scanner.nextLine();
			ProductServiceImpl dao = new ProductServiceImpl();
			dao.get(model);

			System.out.println(" ");

			Common.chooseOperation(scanner);
		} catch (Exception e) {
			System.out.println("Incorrectly entered model! Try again.\n");
			Common.chooseOperation(scanner);
		}
	}

	public static void enterProductData(Scanner scanner) {

		Map<String, String> data = new HashMap<String, String>();

		System.out.println("Please, enter the data of product:");
		try {
			System.out.println("model: ");
			String model = scanner.nextLine();
			System.out.println("maker: ");
			String maker = scanner.nextLine();
			System.out.println("type: ");
			String type = scanner.nextLine();

			ProductServiceImpl dao = new ProductServiceImpl();

			data.put("model", model);
			data.put("maker", maker);
			data.put("type", type);

			dao.save(data);
			
			Common.chooseOperation(scanner);
		} catch (Exception e) {
			System.out.println("Incorrectly entered data! Try again.\n");
			Common.chooseOperation(scanner);
		}
	}

	public static void getProductRecordList(Scanner scanner) {
		ProductServiceImpl dao = new ProductServiceImpl();
		dao.getAll();

		System.out.println(" ");

		Common.chooseOperation(scanner);
	}

	public static void updateProductRecord(Scanner scanner) {
		// TODO Auto-generated method stub

	}
}
