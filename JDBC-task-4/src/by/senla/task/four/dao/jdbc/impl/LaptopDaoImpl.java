package by.senla.task.four.dao.jdbc.impl;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import by.senla.task.four.dao.api.ILaptopDao;
import by.senla.task.four.dao.api.table.ILaptop;
import by.senla.task.four.dao.jdbc.impl.entity.Laptop;
import by.senla.task.four.dao.jdbc.impl.entity.Product;

public class LaptopDaoImpl extends AbstractDaoImpl<ILaptop, Integer> implements ILaptopDao {

	private static final Logger LOGGER = Logger.getLogger(LaptopDaoImpl.class.getName());

	@Override
	public ILaptop createEntity() {
		return new Laptop();
	}

	@Override
	public void update(ILaptop entity) {
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(String.format(
					"update %s set model=?, speed=?, ram=?, hd=?, screen=?, price=? where id=?", getTableName()));
			preparedStatement.setObject(1, entity.getModel().getModel());
			preparedStatement.setInt(2, entity.getSpeed());
			preparedStatement.setInt(3, entity.getRam());
			preparedStatement.setInt(4, entity.getHd());
			preparedStatement.setInt(5, entity.getScreen());
			preparedStatement.setBigDecimal(6, entity.getPrice());
			preparedStatement.setInt(7, entity.getId());
			preparedStatement.executeUpdate();

			preparedStatement.close();

			LOGGER.log(Level.INFO, "Entity has been updated");

		} catch (SQLException | IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to update entity", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void insert(ILaptop entity) {
		try {
			PreparedStatement preparedStatement = getConnection().prepareStatement(
					(String.format("insert into %s (model, speed, ram, hd, screen, price) values(?,?,?,?,?,?)",
							getTableName())),
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setObject(1, entity.getModel().getModel());
			preparedStatement.setInt(2, entity.getSpeed());
			preparedStatement.setInt(3, entity.getRam());
			preparedStatement.setInt(4, entity.getHd());
			preparedStatement.setInt(5, entity.getScreen());
			preparedStatement.setBigDecimal(6, entity.getPrice());
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
		return "laptop";
	}

	@Override
	protected ILaptop parseRow(ResultSet resultSet) throws SQLException {
		final ILaptop entity = createEntity();
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
