package by.senla.cvs.module.processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CsvReader {

	public Map<String, List<String[]>> readFromCsv(File folder) throws FileNotFoundException, IOException {

		Map<String, List<String[]>> strObjectsMap = new HashMap<String, List<String[]>>();

		if (folder.exists()) {

			File[] filesList = folder.listFiles();
			for (File file : filesList) {

				String className = file.getName().split(".csv", 2)[0];
				List<String[]> strObjectsList = new ArrayList<>();

				try (BufferedReader br = new BufferedReader(new FileReader(file));) {

					String strLine;
					while ((strLine = br.readLine()) != null) {
						String[] fieldsArray = strLine.split(";");
						strObjectsList.add(fieldsArray);
					}
				}
				strObjectsMap.put(className, strObjectsList);
			}
		}
		return strObjectsMap;
	}
}
