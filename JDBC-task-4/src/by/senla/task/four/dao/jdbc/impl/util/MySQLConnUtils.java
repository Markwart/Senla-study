package by.senla.task.four.dao.jdbc.impl.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import by.senla.task.four.dao.jdbc.impl.AbstractDaoImpl;

public class MySQLConnUtils {

	private static final Logger LOGGER = Logger.getLogger(AbstractDaoImpl.class.getName());
	private static final String PATH_TO_PROPERTIES = "resources/jdbc.properties";
	private static Connection connection;

	private MySQLConnUtils() {
	}

	public static Connection getConnection() throws SQLException, IOException {
		if (connection == null) {
			connection = getMySQLConnection();
		}
		return connection;
	}

	private static Connection getMySQLConnection() throws IOException {

		try (BufferedReader br = new BufferedReader(new FileReader(PATH_TO_PROPERTIES));) {
			Properties prop = new Properties();
			prop.load(br);

			String hostName = prop.getProperty("jdbc.host");
			String dbName = prop.getProperty("jdbc.db");
			String userName = prop.getProperty("jdbc.user");
			String password = prop.getProperty("jdbc.password");
			String port = prop.getProperty("jdbc.port");

			return getMySQLConnection(hostName, dbName, userName, password, port);
		}
	}

	private static Connection getMySQLConnection(String hostName, String dbName, String userName, String password,
			String port) {

		String connectionURL = "jdbc:mysql://" + hostName + ":" + port + "/" + dbName + "?serverTimezone=UTC";

		Connection connection = null;
		try {
			connection = DriverManager.getConnection(connectionURL, userName, password);
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Connection failure", e);
			throw new RuntimeException(e);
		}
		return connection;
	}
}