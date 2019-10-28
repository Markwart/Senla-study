package by.senla.study.project.dao.jdbc.impl.parser;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.senla.study.project.entity.Printer;
import by.senla.study.project.entity.Product;

public class PrinterParcer {

	public static Printer parseRow(ResultSet resultSet) throws SQLException {
		Printer entity = new Printer();
		entity.setId(resultSet.getInt("id"));
		entity.setType(resultSet.getString("type"));
		entity.setPrice(resultSet.getBigDecimal("price"));
		entity.setColor(resultSet.getString("color").charAt(0));

		String model = (String) resultSet.getObject("model");
		if (model != null) {
			Product product = new Product();
			product.setModel(model);
			entity.setModel(product);
		}
		return entity;
	}
}
