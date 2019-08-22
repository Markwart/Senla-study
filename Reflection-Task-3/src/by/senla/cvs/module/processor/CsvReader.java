package by.senla.cvs.module.processor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CsvReader {

	public static void readFromCsv(String className) throws FileNotFoundException, IOException {

		File folder = new File("./data/");
		if (folder.exists()) {

			File[] listOfFiles = folder.listFiles();
			for (File file : listOfFiles) {

				try (BufferedReader br = new BufferedReader(new FileReader(file));) {

					String strLine;
					while ((strLine = br.readLine()) != null) {

						if (strLine.contains(className)) {
							String[] wordsArray = strLine.split(",");
							for (String str : wordsArray) {
								System.out.print(new StringBuilder(str).append(","));
							}
							System.out.print(System.getProperty("line.separator"));
						}
					}
				}
			}
		}
	}
}
