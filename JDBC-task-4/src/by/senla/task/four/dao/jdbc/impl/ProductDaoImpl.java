package by.senla.task.four.dao.jdbc.impl;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import by.senla.task.four.dao.api.IProductDao;
import by.senla.task.four.dao.api.table.IProduct;
import by.senla.task.four.dao.jdbc.impl.entity.Product;

public class ProductDaoImpl extends AbstractDaoImpl<IProduct, Integer> implements IProductDao {

	private static final Logger LOGGER = Logger.getLogger(ProductDaoImpl .class.getName());

	@Override
	public IProduct createEntity() {
		return new Product();
	}

	@Override
	public void update(IProduct entity) {
		try {
			PreparedStatement preparedStatement = getConnection()
					.prepareStatement(String.format("update %s set maker=?, type=? where model=?", getTableName()));
			preparedStatement.setString(1, entity.getMaker());
			preparedStatement.setString(2, entity.getType());
			preparedStatement.setString(3, entity.getModel());
			preparedStatement.executeUpdate();
			preparedStatement.close();

			LOGGER.log(Level.INFO, "Entity has been updated");

		} catch (SQLException | IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to update entity", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void insert(IProduct entity) {
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(
					String.format("insert into %s (model, maker, type) values(?,?,?)", getTableName()));
			preparedStatement.setString(1, entity.getModel());
			preparedStatement.setString(2, entity.getMaker());
			preparedStatement.setString(3, entity.getType());
			preparedStatement.executeUpdate();
			preparedStatement.close();

			LOGGER.log(Level.INFO, "Entity has been created");

		} catch (SQLException | IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to insert entity", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	protected String getTableName() {
		return "product";
	}

	@Override
	public IProduct get(String model) {
		IProduct result = null;
		try {
			Statement statement = getConnection().createStatement();
			ResultSet resultSet = statement
					.executeQuery("select * from " + getTableName() + " where model = \'" + model + "\'");

			boolean hasNext = resultSet.next();
			if (hasNext) {
				result = parseRow(resultSet);
			}
			statement.close();

		} catch (SQLException | IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to get entity", e);
			throw new RuntimeException(e);
		}
		return result;
	}

	@Override
	public void delete(String model) {
		try {
			PreparedStatement preparedStatement = getConnection()
					.prepareStatement(String.format("delete from %s where model=?", getTableName()));
			preparedStatement.setObject(1, model);
			preparedStatement.executeUpdate();
			preparedStatement.close();

			LOGGER.log(Level.INFO, "Entity has been deleted");

		} catch (SQLException | IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to delete entity", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	protected IProduct parseRow(ResultSet resultSet) throws SQLException {
		IProduct entity = createEntity();
		entity.setModel(resultSet.getString("model"));
		entity.setMaker(resultSet.getString("maker"));
		entity.setType(resultSet.getString("type"));
		return entity;
	}
}
