package by.senla.cvs.module.processor;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import by.senla.cvs.module.annotations.CsvEntity;

public class AnnotationProcessor {

	public static void inspectClass(Object someObject)
			throws IOException, IllegalArgumentException, IllegalAccessException {

		createFolder();

		List<Object> annObjects = new ArrayList<>();

		if (someObject.getClass().isAnnotationPresent(CsvEntity.class)) {
			annObjects.add(someObject);
		}

		CsvWriter.writeToCsv(annObjects);
	}

	private static void createFolder() {
		File folder = new File("./data");
		if (!folder.isDirectory()) {
			folder.mkdir();
		}
	}
}
