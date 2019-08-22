package by.senla.cvs.module.processor;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import by.senla.cvs.module.annotations.CsvEntity;
import by.senla.cvs.module.annotations.CsvProperty;

public class AnnotationProcessor {

	public static void inspectClass(Object someObject)
			throws IOException, IllegalArgumentException, IllegalAccessException {

		createFolder();

		if (someObject.getClass().isAnnotationPresent(CsvEntity.class)) {
			CsvEntity annClass = someObject.getClass().getAnnotation(CsvEntity.class);

			Field[] fields = someObject.getClass().getDeclaredFields();
			List<Field> annFields = new ArrayList<Field>();

			for (Field field : fields) {
				field.setAccessible(true);

				if (field.isAnnotationPresent(CsvProperty.class)) {
					annFields.add(field);
					System.out.println(field.get(someObject));
				}
			}
			writeCSVFile(annClass, annFields, someObject);
		}
	}

	private static void writeCSVFile(CsvEntity annClass, List<Field> annFields, Object someObject)
			throws IOException, IllegalArgumentException, IllegalAccessException {
		try (BufferedWriter wr = new BufferedWriter(new FileWriter("C://Entities/" + annClass.fileName(), true));) {
			for (Field field : annFields) {
				wr.write(field.get(someObject) + annClass.valuesSeparator());
			}
			wr.write(System.getProperty("line.separator"));
		}
	}

	private static void createFolder() {
		File folder = new File("C://Entities");
		folder.mkdir();
	}
}
