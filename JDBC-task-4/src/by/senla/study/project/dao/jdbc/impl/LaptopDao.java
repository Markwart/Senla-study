package by.senla.study.project.dao.jdbc.impl;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import by.senla.study.project.dao.ILaptopDao;
import by.senla.study.project.dao.jdbc.impl.parser.LaptopParser;
import by.senla.study.project.entity.Laptop;

public class LaptopDao extends AbstractDao<Laptop, Integer> implements ILaptopDao {

	private static final Logger LOGGER = Logger.getLogger(LaptopDao.class.getName());
	private static final String TABLE = "laptop";
	private static LaptopDao instance;

	private LaptopDao() {
	}

	public static LaptopDao getInstance() {
		if (instance == null) {
			instance = new LaptopDao();
		}
		return instance;
	}

	@Override
	public void update(Laptop entity) {
		try (PreparedStatement preparedStatement = getConnection().prepareStatement(String
				.format("update %s set model=?, speed=?, ram=?, hd=?, screen=?, price=? where id=?", getTableName()))) {
			preparedStatement.setObject(1, entity.getModel().getModel());
			preparedStatement.setInt(2, entity.getSpeed());
			preparedStatement.setInt(3, entity.getRam());
			preparedStatement.setInt(4, entity.getHd());
			preparedStatement.setInt(5, entity.getScreen());
			preparedStatement.setBigDecimal(6, entity.getPrice());
			preparedStatement.setInt(7, entity.getId());
			preparedStatement.executeUpdate();

			LOGGER.log(Level.INFO, "Entity has been updated");

		} catch (SQLException | IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to update entity", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void insert(Laptop entity) {
		try (PreparedStatement preparedStatement = getConnection().prepareStatement((String
				.format("insert into %s (model, speed, ram, hd, screen, price) values(?,?,?,?,?,?)", getTableName())),
				Statement.RETURN_GENERATED_KEYS)) {
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

			LOGGER.log(Level.INFO, "Entity has been created with id=" + id);

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
	protected Laptop parseRow(ResultSet resultSet) throws SQLException {
		return LaptopParser.parseRow(resultSet);
	}
}
