package by.senla.study.project.dao.jdbc.impl.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PropertyReader {

	private static final Logger LOGGER = Logger.getLogger(PropertyReader.class.getName());
	private static final String PATH_TO_PROPERTIES = "resources/jdbc.properties";

	public static Map<String, String> readFromProperties() {

		Map<String, String> properties = new HashMap<String, String>();

		try (BufferedReader br = new BufferedReader(new FileReader(PATH_TO_PROPERTIES));) {
			Properties prop = new Properties();
			prop.load(br);

			String connectionURL = prop.getProperty("jdbc.connectionURL");
			String userName = prop.getProperty("jdbc.user");
			String password = prop.getProperty("jdbc.password");
			
			properties.put("connectionURL", connectionURL);
			properties.put("userName", userName);
			properties.put("password", password);

		} catch (IOException e) {
			LOGGER.log(Level.SEVERE, "Failed to read properties", e);
			throw new RuntimeException(e);
		}
		return properties;
	}

}
