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

	public static Map<String, List<String[]>> readFromCsv(File folder) throws FileNotFoundException, IOException {

		Map<String, List<String[]>> strObjMap = new HashMap<String, List<String[]>>();

		if (folder.exists()) {

			File[] filesList = folder.listFiles();
			for (File file : filesList) {

				String key = file.getName().split(".csv", 2)[0];
				List<String[]> strList = new ArrayList<>();

				try (BufferedReader br = new BufferedReader(new FileReader(file));) {

					String strLine;
					while ((strLine = br.readLine()) != null) {
						String[] wordsArray = strLine.split(";");
						strList.add(wordsArray);
					}
				}
				strObjMap.put(key, strList);
			}
		}
		return strObjMap;
	}
}
