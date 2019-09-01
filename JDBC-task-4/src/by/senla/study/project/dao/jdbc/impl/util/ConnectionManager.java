package by.senla.study.project.dao.jdbc.impl.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import by.senla.study.project.dao.jdbc.impl.AbstractDaoImpl;

public class ConnectionManager {

	private static final Logger LOGGER = Logger.getLogger(AbstractDaoImpl.class.getName());
	private static final String PATH_TO_PROPERTIES = "resources/jdbc.properties";

	private Connection connection;
	private static ConnectionManager instance;

	private ConnectionManager() throws FileNotFoundException, IOException {
		connection = getConnection();
	}

	public static ConnectionManager getInstance() throws SQLException, IOException {
		if (instance == null) {
			instance = new ConnectionManager();
		}
		return instance;
	}

	public Connection getConnection() throws FileNotFoundException, IOException {

		Connection connection;

		try (BufferedReader br = new BufferedReader(new FileReader(PATH_TO_PROPERTIES));) {
			Properties prop = new Properties();
			prop.load(br);

			String connectionURL = prop.getProperty("jdbc.connectionURL");
			String userName = prop.getProperty("jdbc.user");
			String password = prop.getProperty("jdbc.password");

			connection = DriverManager.getConnection(connectionURL, userName, password);

		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Connection failure", e);
			throw new RuntimeException(e);
		}
		return connection;
	}

	public static void closeConnection() throws FileNotFoundException, SQLException, IOException {
		getInstance().getConnection().close();
	}
}
