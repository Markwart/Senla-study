package by.senla.cvs.module.processor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import by.senla.cvs.module.annotations.CsvEntity;
import by.senla.cvs.module.annotations.CsvProperty;
import by.senla.cvs.module.enums.PropertyType;

public class CsvWriter {

	private static final Logger LOGGER = Logger.getLogger(CsvWriter.class.getName());
	private static final String PATH_TO_PROPERTIES = "resources/config.properties";

	public void writeToCsv(List<Object> annotatedObjects) throws IOException {

		writeFieldName(annotatedObjects);

		for (Object someObject : annotatedObjects) {

			CsvEntity annClass = someObject.getClass().getAnnotation(CsvEntity.class);
			Map<Integer, Field> annFieldsMap = findAnnFields(someObject);

			try (BufferedWriter wr = new BufferedWriter(new FileWriter(getPath() + annClass.fileName(), true));) {

				annFieldsMap.forEach((key, field) -> {
					try {
						CsvProperty annotatedField = field.getAnnotation(CsvProperty.class);
						Object csvField = defineTypeField(someObject, field, annotatedField, annotatedObjects);

						wr.write(new StringBuilder().append(csvField).append(annClass.valuesSeparator()).toString());
					} catch (IOException | NoSuchFieldException | IllegalAccessException e) {
						LOGGER.log(Level.SEVERE, "Exception", e);
						throw new RuntimeException(e);
					}
				});
				wr.write(System.getProperty("line.separator"));
			}
		}
		writeClassName(annotatedObjects);
	}

	private String getPath() throws IOException, FileNotFoundException {
		String path;
		try (BufferedReader br = new BufferedReader(new FileReader(PATH_TO_PROPERTIES));) {
			Properties property = new Properties();
			property.load(br);
			path = property.getProperty("file_folder");
		}
		return path;
	}

	private void writeClassName(List<Object> annObjects) throws IOException, FileNotFoundException {
		for (Object someObject : annObjects) {
			CsvEntity annotatedClass = someObject.getClass().getAnnotation(CsvEntity.class);
			try (BufferedWriter wr = new BufferedWriter(new FileWriter(getPath() + annotatedClass.fileName(), true));) {
				List<String> lines = Files.readAllLines(Paths.get(getPath() + annotatedClass.fileName()));
				if (!lines.contains(someObject.getClass().getName())) {
					wr.write(someObject.getClass().getName());
				}
			}
		}
	}

	private void writeFieldName(List<Object> annotatedObjects) throws IOException {

		for (Object someObject : annotatedObjects) {

			CsvEntity annotatedClass = someObject.getClass().getAnnotation(CsvEntity.class);
			Map<Integer, Field> annotatedFieldsMap = findAnnFields(someObject);

			try (BufferedWriter wr = new BufferedWriter(
					new FileWriter(getPath() + annotatedClass.fileName(), false));) {

				annotatedFieldsMap.forEach((key, field) -> {
					try {
						List<String> lines = Files.readAllLines(Paths.get(getPath() + annotatedClass.fileName()));

						if (!lines.contains(field.getName())) {
							wr.write(new StringBuilder(field.getName()).append(annotatedClass.valuesSeparator())
									.toString());
						}
					} catch (IOException e) {
						LOGGER.log(Level.SEVERE, "Exception", e);
						throw new RuntimeException(e);
					}
				});
				wr.write(System.getProperty("line.separator"));
			}
		}
	}

	private Object defineTypeField(Object someObject, Field field, CsvProperty annotatedField,
			List<Object> annotatedObjects) throws NoSuchFieldException, IllegalAccessException {

		Object csvField;

		if (annotatedField.propertyType() == PropertyType.CompositeProperty) {
			Object compositeField = field.get(someObject);
			String keyFieldValue = null;

			for (Object relatedObject : annotatedObjects) {
				if (relatedObject.equals(compositeField)) {
					Field relatedObjectField = relatedObject.getClass().getDeclaredField(annotatedField.keyField());
					relatedObjectField.setAccessible(true);
					keyFieldValue = relatedObjectField.get(relatedObject).toString();
				}
			}
			csvField = new StringBuilder().append(compositeField.getClass().getSimpleName()).append("::")
					.append(keyFieldValue);
		} else {
			csvField = field.get(someObject);
		}
		return csvField;
	}

	private Map<Integer, Field> findAnnFields(Object someObject) {
		Field[] fields = someObject.getClass().getDeclaredFields();
		Map<Integer, Field> annotatedFieldsMap = new HashMap<Integer, Field>();

		for (Field field : fields) {
			field.setAccessible(true);
			if (field.isAnnotationPresent(CsvProperty.class)) {
				annotatedFieldsMap.put(field.getAnnotation(CsvProperty.class).columnNumber(), field);
			}
		}
		return annotatedFieldsMap;
	}
}
