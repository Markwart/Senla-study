package by.senla.study.project.dao.jdbc.impl.parser;

import java.sql.ResultSet;
import java.sql.SQLException;

import by.senla.study.project.dao.jdbc.impl.entity.Product;

public class ProductParcer {

	public static Product parseRow(ResultSet resultSet) throws SQLException {
		Product entity = new Product();
		entity.setModel(resultSet.getString("model"));
		entity.setMaker(resultSet.getString("maker"));
		entity.setType(resultSet.getString("type"));
		return entity;
	}
}
