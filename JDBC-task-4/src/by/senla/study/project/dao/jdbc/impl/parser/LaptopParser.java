package by.senla.study.project.dao.jdbc.impl.parser;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.senla.study.project.entity.Laptop;
import by.senla.study.project.entity.Product;

public class LaptopParser {

	public static Laptop parseRow(ResultSet resultSet) throws SQLException {
		Laptop entity = new Laptop();
		entity.setId(resultSet.getInt("id"));
		entity.setHd(resultSet.getInt("hd"));
		entity.setRam(resultSet.getInt("ram"));
		entity.setSpeed(resultSet.getInt("speed"));
		entity.setScreen(resultSet.getInt("screen"));
		entity.setPrice(resultSet.getBigDecimal("price"));

		String model = (String) resultSet.getObject("model");
		if (model != null) {
			Product product = new Product();
			product.setModel(model);
			entity.setModel(product);
		}
		return entity;
	}
}
