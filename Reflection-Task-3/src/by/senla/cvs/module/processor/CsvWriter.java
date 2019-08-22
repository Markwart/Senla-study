package by.senla.cvs.module.processor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import by.senla.cvs.module.annotations.CsvEntity;
import by.senla.cvs.module.annotations.CsvProperty;

public class CsvWriter {

	public static void writeToCsv(List<Object> annObjects)
			throws IOException, IllegalArgumentException, IllegalAccessException {

		for (Object someObject : annObjects) {
			CsvEntity annClass = someObject.getClass().getAnnotation(CsvEntity.class);

			List<Field> annFields = findAnnFields(someObject);

			try (BufferedWriter wr = new BufferedWriter(new FileWriter("./data/" + annClass.fileName(), true));) {

				wr.write(new StringBuilder().append(someObject.getClass().getSimpleName())
						.append(annClass.valuesSeparator()).toString());

				for (Field field : annFields) {
					wr.write(new StringBuilder().append(field.get(someObject)).append(annClass.valuesSeparator())
							.toString());
				}
				wr.write(System.getProperty("line.separator"));
			}
		}
	}

	private static List<Field> findAnnFields(Object someObject) {
		Field[] fields = someObject.getClass().getDeclaredFields();
		List<Field> annFields = new ArrayList<>();

		for (Field field : fields) {
			field.setAccessible(true);

			if (field.isAnnotationPresent(CsvProperty.class)) {
				annFields.add(field);
				// System.out.println(field.get(someObject));
			}
		}
		return annFields;
	}
}
