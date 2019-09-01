package by.senla.study.project.dao.jdbc.impl;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import by.senla.study.project.dao.IWithConditionDao;
import by.senla.study.project.dao.jdbc.impl.entity.PC;
import by.senla.study.project.dao.jdbc.impl.parser.PCParcer;
import by.senla.study.project.dao.jdbc.impl.util.ConnectionManager;

public class WithConditionDao implements IWithConditionDao {

	private static final Logger LOGGER = Logger.getLogger(WithConditionDao.class.getName());
	private static WithConditionDao instance;

	private WithConditionDao() {
	}

	public static WithConditionDao getInstance() {
		if (instance == null) {
			instance = new WithConditionDao();
		}
		return instance;
	}

	private Connection getConnection() throws SQLException, IOException {
		return ConnectionManager.getInstance().getConnection();
	}

	@Override
	public List<PC> findPCWithCost() {
		List<PC> resultList = new ArrayList<>();
		try (Statement statement = getConnection().createStatement()) {
			ResultSet resultSet = statement.executeQuery("select * from pc where price < 500");

			while (resultSet.next()) {
				resultList.add(PCParcer.parseRow(resultSet));
			}
		} catch (SQLException | IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to get result list", e);
			throw new RuntimeException(e);
		}
		return resultList;
	}

	@Override
	public Map<Integer, Integer> findAverageCostPc() {
		Map<Integer, Integer> resultList = new HashMap<Integer, Integer>();
		try (Statement statement = getConnection().createStatement()) {
			ResultSet resultSet = statement.executeQuery("select speed, avg(price) from pc group by speed");

			while (resultSet.next()) {
				Integer speed = resultSet.getInt(1);
				Integer price = resultSet.getInt(2);
				resultList.put(speed, price);
			}
		} catch (SQLException | IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to get result list", e);
			throw new RuntimeException(e);
		}
		return resultList;
	}

	@Override
	public List<String> findPrinterMakers() {
		List<String> resultList = new ArrayList<>();
		try (Statement statement = getConnection().createStatement()) {
			ResultSet resultSet = statement
					.executeQuery("select maker from product where type = 'printer' group by maker");

			while (resultSet.next()) {
				String maker = resultSet.getString(1);
				resultList.add(maker);
			}
		} catch (SQLException | IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to get result list", e);
			throw new RuntimeException(e);
		}
		return resultList;
	}

	@Override
	public List<String> findPCAndLaptopMakers() {
		List<String> resultList = new ArrayList<>();
		try (Statement statement = getConnection().createStatement()) {
			ResultSet resultSet = statement.executeQuery(
					"select distinct maker from pc inner join product on product.model = pc.model where pc.speed >= 750 \r\n"
							+ "and product.maker in (select maker from laptop inner join product on product.model = laptop.model where laptop.speed >= 750)");

			while (resultSet.next()) {
				String maker = resultSet.getString(1);
				resultList.add(maker);
			}
		} catch (SQLException | IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to get result list", e);
			throw new RuntimeException(e);
		}
		return resultList;
	}
}
