package by.senla.study.project.dao.jdbc.impl;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import by.senla.study.project.dao.IPrinterDao;
import by.senla.study.project.dao.jdbc.impl.entity.Printer;
import by.senla.study.project.dao.jdbc.impl.parser.PrinterParcer;

public class PrinterDao extends AbstractDao<Printer, Integer> implements IPrinterDao {

	private static final Logger LOGGER = Logger.getLogger(PrinterDao.class.getName());
	private static PrinterDao instance;

	private PrinterDao() {
	}

	public static PrinterDao getInstance() {
		if (instance == null) {
			instance = new PrinterDao();
		}
		return instance;
	}

	@Override
	public void update(Printer entity) {
		try (PreparedStatement preparedStatement = getConnection().prepareStatement(
				String.format("update %s set model=?, color=?, type=?, price=? where id=?", getTableName()))) {
			preparedStatement.setObject(1, entity.getModel().getModel());
			preparedStatement.setString(2, entity.getColor().toString());
			preparedStatement.setString(3, entity.getType());
			preparedStatement.setBigDecimal(4, entity.getPrice());
			preparedStatement.setInt(5, entity.getId());
			preparedStatement.executeUpdate();

			LOGGER.log(Level.INFO, "Entity has been updated");

		} catch (SQLException | IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to update entity", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void insert(Printer entity) {
		try (PreparedStatement preparedStatement = getConnection().prepareStatement(
				(String.format("insert into %s (model, color, type, price) values(?,?,?,?)", getTableName())),
				Statement.RETURN_GENERATED_KEYS)) {
			preparedStatement.setObject(1, entity.getModel().getModel());
			preparedStatement.setString(2, entity.getColor().toString());
			preparedStatement.setString(3, entity.getType());
			preparedStatement.setBigDecimal(4, entity.getPrice());
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
		return "printer";
	}

	@Override
	protected Printer parseRow(ResultSet resultSet) throws SQLException {
		return PrinterParcer.parseRow(resultSet);
	}
}
