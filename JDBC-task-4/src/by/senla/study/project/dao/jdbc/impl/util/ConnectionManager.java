package by.senla.study.project.dao.jdbc.impl.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionManager {

	private static final Logger LOGGER = Logger.getLogger(ConnectionManager.class.getName());

	private Connection connection;
	private static ConnectionManager instance;

	private ConnectionManager() throws FileNotFoundException, IOException {
		connection = createConnection();
	}

	public static ConnectionManager getInstance() throws SQLException, IOException {
		if (instance == null) {
			instance = new ConnectionManager();
		}
		return instance;
	}

	public Connection getConnection() throws FileNotFoundException, IOException {
		return connection;
	}

	private Connection createConnection() {
		Map<String, String> properties = PropertyReader.readFromProperties();
		Connection connection;
		try {
			connection = DriverManager.getConnection(properties.get("connectionURL"), properties.get("userName"),
					properties.get("password"));
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
