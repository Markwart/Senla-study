package by.senla.study.project.dao.jdbc.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import by.senla.study.project.dao.IProductDao;
import by.senla.study.project.dao.jdbc.impl.parser.ProductParcer;
import by.senla.study.project.entity.Product;

public class ProductDao extends AbstractDao<Product, String> implements IProductDao {

	private static final Logger LOGGER = Logger.getLogger(ProductDao.class.getName());
	private static final String TABLE = "product";
	private static ProductDao instance;

	private ProductDao() {
	}

	public static ProductDao getInstance() {
		if (instance == null) {
			instance = new ProductDao();
		}
		return instance;
	}

	@Override
	public void update(Product entity) {
		try (PreparedStatement preparedStatement = getConnection()
				.prepareStatement(String.format("update %s set maker=?, type=? where model=?", getTableName()))) {
			preparedStatement.setString(1, entity.getMaker());
			preparedStatement.setString(2, entity.getType());
			preparedStatement.setString(3, entity.getModel());
			preparedStatement.executeUpdate();

			LOGGER.log(Level.INFO, "Entity has been updated");

		} catch (SQLException | IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to update entity", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void insert(Product entity) {
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = getConnection().prepareStatement(
						String.format("insert into %s (model, maker, type) values(?,?,?)", getTableName()))) {
			connection.setAutoCommit(false);
			try {
				preparedStatement.setString(1, entity.getModel());
				preparedStatement.setString(2, entity.getMaker());
				preparedStatement.setString(3, entity.getType());
				preparedStatement.executeUpdate();
				connection.commit();
				LOGGER.log(Level.INFO, "Entity has been created");
			} catch (Exception e) {
				connection.rollback();
				throw new RuntimeException(e);
			}
		} catch (SQLException | IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to insert entity", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	protected String getTableName() {
		return TABLE;
	}

	@Override
	public Product get(String model) {
		Product result = null;
		try (Statement statement = getConnection().createStatement()) {
			ResultSet resultSet = statement
					.executeQuery("select * from " + getTableName() + " where model = \'" + model + "\'");

			if (resultSet.next()) {
				result = parseRow(resultSet);
			}

		} catch (SQLException | IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to get entity", e);
			throw new RuntimeException(e);
		}
		return result;
	}

	@Override
	public void delete(String model) {
		try (PreparedStatement preparedStatement = getConnection()
				.prepareStatement(String.format("delete from %s where model=?", getTableName()))) {
			preparedStatement.setObject(1, model);
			preparedStatement.executeUpdate();

			LOGGER.log(Level.INFO, "Entity has been deleted");

		} catch (SQLException | IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to delete entity", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	protected Product parseRow(ResultSet resultSet) throws SQLException {
		return ProductParcer.parseRow(resultSet);
	}

	@Override
	public List<Product> findPrinterMakers() {
		List<Product> resultList = new ArrayList<>();
		try (Statement statement = getConnection().createStatement()) {
			ResultSet resultSet = statement.executeQuery(
					(String.format("select * from %s where type = 'printer' group by maker", getTableName())));

			while (resultSet.next()) {
				resultList.add(parseRow(resultSet));
			}
		} catch (SQLException | IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to get result list", e);
			throw new RuntimeException(e);
		}
		return resultList;
	}

	@Override
	public List<Product> findPCAndLaptopMakers(Integer speed) {
		List<Product> resultList = new ArrayList<>();
		try (Statement statement = getConnection().createStatement()) {
			ResultSet resultSet = statement.executeQuery((String.format(
					"select * from %s inner join pc on product.model = pc.model where pc.speed >= %s \r\n"
							+ "and product.maker in (select maker from %s inner join laptop on product.model = laptop.model where laptop.speed >= %s)",
					getTableName(), speed, getTableName(), speed)));

			while (resultSet.next()) {
				resultList.add(parseRow(resultSet));
			}
		} catch (SQLException | IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to get result list", e);
			throw new RuntimeException(e);
		}
		return resultList;
	}
}
