package by.senla.task.four.dao.jdbc.impl;

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

import by.senla.task.four.dao.api.IDao;
import by.senla.task.four.dao.jdbc.impl.util.MySQLConnUtils;

public abstract class AbstractDaoImpl<ENTITY, ID> implements IDao<ENTITY, ID> {

	private static final Logger LOGGER = Logger.getLogger(AbstractDaoImpl.class.getName());

	protected Connection getConnection() throws SQLException, IOException {
		return MySQLConnUtils.getConnection();
	}

	protected abstract String getTableName();

	@Override
	public ENTITY get(ID id) {
		ENTITY result = null;
		try {
			Statement statement = getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("select * from " + getTableName() + " where id = \'" + id + "\'");

			if (resultSet.next()) {
				result = parseRow(resultSet);
			}
			statement.close();

		} catch (SQLException | IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to get entity", e);
			throw new RuntimeException(e);
		}
		return result;
	}

	protected ENTITY parseRow(final ResultSet resultSet) throws SQLException {
		return parseRow(resultSet);
	}

	@Override
	public List<ENTITY> selectAll() {
		List<ENTITY> resultList = new ArrayList<>();
		try {
			Statement statement = getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("select * from " + getTableName());

			while (resultSet.next()) {
				resultList.add(parseRow(resultSet));
			}
			statement.close();

		} catch (SQLException | IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to get list of entity", e);
			throw new RuntimeException(e);
		}
		return resultList;
	}

	@Override
	public void delete(ID id) {
		try {
			PreparedStatement preparedStatement = getConnection()
					.prepareStatement(String.format("delete from %s where id = ?", getTableName()));
			preparedStatement.setObject(1, id);
			preparedStatement.executeUpdate();
			preparedStatement.close();

			LOGGER.log(Level.INFO, "Entity has been deleted");

		} catch (SQLException | IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to delete entity", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void deleteAll() {
		try {
			PreparedStatement preparedStatement = getConnection()
					.prepareStatement(String.format("delete from %s", getTableName()));
			preparedStatement.executeUpdate();
			preparedStatement.close();

			LOGGER.log(Level.INFO, "Data from table has been deleted");

		} catch (SQLException | IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to delete data from table", e);
			throw new RuntimeException(e);
		}
	}
}
