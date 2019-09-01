package by.senla.study.project.dao.jdbc.impl;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import by.senla.study.project.dao.IPCDao;
import by.senla.study.project.dao.jdbc.impl.entity.PC;
import by.senla.study.project.dao.jdbc.impl.parser.PCParcer;

public class PCDaoImpl extends AbstractDaoImpl<PC, Integer> implements IPCDao {

	private static final Logger LOGGER = Logger.getLogger(PCDaoImpl.class.getName());
	private static PCDaoImpl instance;

	private PCDaoImpl() {
	}

	public static PCDaoImpl getInstance() {
		if (instance != null) {
			instance = new PCDaoImpl();
		}
		return instance;
	}

	@Override
	public void update(PC entity) {
		try (PreparedStatement preparedStatement = getConnection().prepareStatement(String
				.format("update %s set model=?, speed=?, ram=?, hd=?, cd=?, price=? where id=?", getTableName()))) {
			preparedStatement.setObject(1, entity.getModel().getModel());
			preparedStatement.setInt(2, entity.getSpeed());
			preparedStatement.setInt(3, entity.getRam());
			preparedStatement.setInt(4, entity.getHd());
			preparedStatement.setString(5, entity.getCd());
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
	public void insert(PC entity) {
		try (PreparedStatement preparedStatement = getConnection().prepareStatement((String
				.format("insert into %s (model, speed, ram, hd, cd, price) values(?,?,?,?,?,?)", getTableName())),
				Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setObject(1, entity.getModel().getModel());
			preparedStatement.setInt(2, entity.getSpeed());
			preparedStatement.setInt(3, entity.getRam());
			preparedStatement.setInt(4, entity.getHd());
			preparedStatement.setString(5, entity.getCd());
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
		return "pc";
	}

	protected PC parseRow(ResultSet resultSet) throws SQLException {
		return PCParcer.parseRow(resultSet);
	}
}
