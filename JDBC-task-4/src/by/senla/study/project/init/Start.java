package by.senla.study.project.init;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.senla.study.project.dao.jdbc.impl.LaptopDaoImpl;
import by.senla.study.project.dao.jdbc.impl.entity.Laptop;
import by.senla.study.project.dao.jdbc.impl.entity.Printer;
import by.senla.study.project.dao.jdbc.impl.entity.Product;
import by.senla.study.project.service.impl.PrinterServiceImpl;
import by.senla.study.project.service.impl.ProductServiceImpl;

public class Start {

	public static void main(String[] args)
			throws ClassNotFoundException, SQLException, FileNotFoundException, IOException {

		ProductServiceImpl daoProduct = ProductServiceImpl.getInstance();

		Product entity1 = daoProduct.get("L-A2");
		System.out.println(entity1.getMaker() + " " + entity1.getModel() + " " + entity1.getType());

		Map<String, String> dataProduct = new HashMap<String, String>();
		dataProduct.put("maker", "Acer");
		dataProduct.put("type", "pc");
		dataProduct.put("model", "Acer-2.01");
		daoProduct.save(dataProduct);
		
		Product entity2 = daoProduct.get("Acer-2.01");
		System.out.println(entity2.getMaker() + " " + entity2.getModel() + " " + entity2.getType());
		
		daoProduct.delete("Acer-2.01");
		entity2 = daoProduct.get("Acer-2.01");
		
		List<Product> entityList = daoProduct.getAll();
		System.out.println(entityList.size());
		
		PrinterServiceImpl daoPrinter = PrinterServiceImpl.getInstance();
		
		/*Map<String, String> dataPrinter = new HashMap<String, String>();
		dataPrinter.put("model", "PR-S2");
		dataPrinter.put("type", "laser");
		dataPrinter.put("color", "y");
		dataPrinter.put("price", "5000");
		daoPrinter.save(dataPrinter);*/
		
		Printer printer = daoPrinter.get(2);
		System.out.println(printer.getModel().getModel() + " " + printer.getType() + " " + printer.getPrice() + " " + printer.getColor() + " " + printer.getId());
		
		Map<String, String> dataPrinter = new HashMap<String, String>();
		dataPrinter.put("price", "500");
		daoPrinter.update(dataPrinter, 2);
		System.out.println(printer.getModel().getModel() + " " + printer.getType() + " " + printer.getPrice() + " " + printer.getColor() + " " + printer.getId());
		
		LaptopDaoImpl daoLaptop = LaptopDaoImpl.getInstance();
		
		List<Laptop> laptopList = daoLaptop.selectAll();
		System.out.println(laptopList.size());
		
		
		/*entity2.setMaker("Toyota");
		entity2.setType("Printer");
		daoProduct.update(entity2);
		
		daoProduct.get("Acer-2.01");
		System.out.println(entity2.getMaker() + " " + entity2.getModel() + " " + entity2.getType());

		LaptopServiceImpl daoLaptop = new LaptopServiceImpl();
		
		
		Laptop newLaptop = daoLaptop.createEntity();
		newLaptop.setHd(10);
		newLaptop.setRam(10);
		newLaptop.setScreen(10);
		newLaptop.setSpeed(10);
		newLaptop.setPrice(new BigDecimal(10));
		newLaptop.setModel(daoProduct.get("L-A1"));
		daoLaptop.insert(newLaptop);
		
		List<Laptop> laptopList = daoLaptop.getAll();
		System.out.println(laptopList.size());*/
	}
}
