package by.senla.study.project.dao.jdbc.impl.parser;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.senla.study.project.dao.jdbc.impl.entity.PC;
import by.senla.study.project.dao.jdbc.impl.entity.Product;

public class PCParcer {

	public static PC parseRow(ResultSet resultSet) throws SQLException {
		PC entity = new PC();
		entity.setId(resultSet.getInt("id"));
		entity.setHd(resultSet.getInt("hd"));
		entity.setRam(resultSet.getInt("ram"));
		entity.setSpeed(resultSet.getInt("speed"));
		entity.setCd(resultSet.getString("cd"));
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
