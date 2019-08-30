package by.senla.task.four.dao.jdbc.impl;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import by.senla.task.four.dao.api.IPrinterDao;
import by.senla.task.four.dao.api.table.IPrinter;
import by.senla.task.four.dao.jdbc.impl.entity.Printer;
import by.senla.task.four.dao.jdbc.impl.entity.Product;

public class PrinterDaoImpl extends AbstractDaoImpl<IPrinter, Integer> implements IPrinterDao {

	private static final Logger LOGGER = Logger.getLogger(PrinterDaoImpl.class.getName());

	@Override
	public IPrinter createEntity() {
		return new Printer();
	}

	@Override
	public void update(IPrinter entity) {
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(
					String.format("update %s set model=?, color=?, type?, price=? where id=?", getTableName()));
			preparedStatement.setObject(1, entity.getModel().getModel());
			preparedStatement.setString(2, entity.getColor().toString());
			preparedStatement.setString(3, entity.getType());
			preparedStatement.setBigDecimal(4, entity.getPrice());
			preparedStatement.setInt(5, entity.getId());
			preparedStatement.executeUpdate();

			preparedStatement.close();

			LOGGER.log(Level.INFO, "Entity has been updated");

		} catch (SQLException | IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to update entity", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void insert(IPrinter entity) {
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(
					(String.format("insert into %s (model, color, type, price) values(?,?,?,?)", getTableName())),
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setObject(1, entity.getModel().getModel());
			preparedStatement.setString(2, entity.getColor().toString());
			preparedStatement.setString(3, entity.getType());
			preparedStatement.setBigDecimal(4, entity.getPrice());
			preparedStatement.executeUpdate();

			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			resultSet.next();
			int id = resultSet.getInt(1);

			preparedStatement.close();

			LOGGER.log(Level.INFO, "Entity has been created with id=" + id);

		} catch (SQLException | IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to insert entity", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	protected String getTableName() {
		return "printer";
	}

	@Override
	protected IPrinter parseRow(ResultSet resultSet) throws SQLException {
		final IPrinter entity = createEntity();
		entity.setId(resultSet.getInt("id"));
		entity.setType(resultSet.getString("type"));
		entity.setPrice(resultSet.getBigDecimal("price"));
		entity.setColor(resultSet.getString("color").charAt(1));

		String model = (String) resultSet.getObject("model");
		if (model != null) {
			Product product = new Product();
			product.setModel(model);
			entity.setModel(product);
		}
		return entity;
	}
}
