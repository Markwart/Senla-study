package by.senla.study.project.init;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import by.senla.study.project.dao.jdbc.impl.entity.Product;
import by.senla.study.project.service.IProductService;
import by.senla.study.project.service.impl.ProductService;

public class ProductOperation {

	private static final Logger LOGGER = Logger.getLogger(ProductOperation.class.getName());
	private static IProductService service = ProductService.getInstance();

	public static void deleteProductRecord(Scanner scanner) {
		System.out.println("Enter \"pk\"(model) \n");
		try {
			String model = scanner.next();
			service.delete(model);
		} catch (Exception e) {
			System.out.println("Incorrectly entered model! Try again.\n");
			LOGGER.log(Level.SEVERE, "Failed to delete record", e);
		} finally {
			Common.chooseOperation(scanner);
		}
	}

	public static void getProductRecord(Scanner scanner) {
		System.out.println("Enter \"pk\"(model) \n");
		try {
			String model = scanner.next();
			Product entity = service.get(model);

			StringBuilder product = new StringBuilder().append(entity.getModel()).append(" ").append(entity.getMaker())
					.append(" ").append(entity.getType());
			System.out.println(product);

		} catch (Exception e) {
			System.out.println("Incorrectly entered model! Try again.\n");
			LOGGER.log(Level.SEVERE, "Failed to get record", e);
		} finally {
			Common.chooseOperation(scanner);
		}
	}

	public static void enterProductData(Scanner scanner) {

		Map<String, String> data = new HashMap<String, String>();

		System.out.println("Please, enter the data of product: \n");
		try {
			System.out.println("model: ");
			String model = scanner.next();
			System.out.println("maker: ");
			String maker = scanner.next();
			System.out.println("type: ");
			String type = scanner.next();

			data.put("model", model);
			data.put("maker", maker);
			data.put("type", type);

			service.save(data);

			System.out.println("Perform");
		} catch (Exception e) {
			System.out.println("Incorrectly entered data! Try again.\n");
			LOGGER.log(Level.SEVERE, "Failed to save data", e);
		} finally {
			Common.chooseOperation(scanner);
		}
	}

	public static void getProductRecordList(Scanner scanner) {
		List<Product> productList = service.getAll();

		System.out.println(productList.size());

		Common.chooseOperation(scanner);
	}

	public static void updateProductRecord(Scanner scanner) {

		Map<String, String> data = new HashMap<String, String>();

		System.out.println("Please, enter the data of product you want to change: \n");
		try {
			System.out.println("Enter \"pk\"(model) \n");
			String model = scanner.next();
			System.out.println("maker: ");
			String maker = scanner.next();
			System.out.println("type: ");
			String type = scanner.next();

			if (maker != null) {
				data.put("maker", maker);
			}
			if (type != null) {
				data.put("type", type);
			}
			service.update(data, model);
		} catch (Exception e) {
			System.out.println("Incorrectly entered data or model! Try again.\n");
			LOGGER.log(Level.SEVERE, "Failed to update record", e);
		} finally {
			Common.chooseOperation(scanner);
		}
	}
}
