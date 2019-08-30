package by.senla.task.four.init;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import by.senla.task.four.dao.api.table.ILaptop;
import by.senla.task.four.dao.api.table.IProduct;
import by.senla.task.four.dao.jdbc.impl.LaptopDaoImpl;
import by.senla.task.four.dao.jdbc.impl.ProductDaoImpl;
import by.senla.task.four.dao.jdbc.impl.entity.Product;

public class Start {

	public static void main(String[] args)
			throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {

		ProductDaoImpl daoProduct = new ProductDaoImpl();

		IProduct entity1 = daoProduct.get("L-A2");
		System.out.println(entity1.getMaker() + " " + entity1.getModel() + " " + entity1.getType());

		IProduct product = daoProduct.createEntity();
		product.setMaker("Acer");
		product.setType("pc");
		product.setModel("Acer-2.01");
		daoProduct.insert(product);

		IProduct entity2 = daoProduct.get("Acer-2.01");
		System.out.println(entity2.getMaker() + " " + entity2.getModel() + " " + entity2.getType());

		entity2.setMaker("Toyota");
		entity2.setType("Printer");
		daoProduct.update(entity2);
		daoProduct.get("Acer-2.01");
		System.out.println(entity2.getMaker() + " " + entity2.getModel() + " " + entity2.getType());

		daoProduct.delete("Acer-2.01");
		entity2 = daoProduct.get("Acer-2.01");

		List<IProduct> entityList = daoProduct.selectAll();
		System.out.println(entityList.size());
		
		LaptopDaoImpl daoLaptop = new LaptopDaoImpl();
		
		daoLaptop.deleteAll();
		
		ILaptop newLaptop = daoLaptop.createEntity();
		newLaptop.setHd(10);
		newLaptop.setRam(10);
		newLaptop.setScreen(10);
		newLaptop.setSpeed(10);
		newLaptop.setPrice(new BigDecimal(10));
		newLaptop.setModel((Product) daoProduct.get("L-A1"));
		daoLaptop.insert(newLaptop);
		
		List<ILaptop> laptopList = daoLaptop.selectAll();
		System.out.println(laptopList.size());
	}
}
