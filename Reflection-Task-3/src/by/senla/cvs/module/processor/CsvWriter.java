package by.senla.cvs.module.processor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import by.senla.cvs.module.annotations.CsvEntity;
import by.senla.cvs.module.annotations.CsvProperty;
import by.senla.cvs.module.enums.PropertyType;

public class CsvWriter {
	
	private static final Logger LOGGER = Logger.getLogger(Parsing.class.getName());

	public void writeToCsv(List<Object> annObjects) throws IOException {

		writeFieldName(annObjects);

		for (Object someObject : annObjects) {

			CsvEntity annClass = someObject.getClass().getAnnotation(CsvEntity.class);
			Map<Integer, Field> annFieldsMap = findAnnFields(someObject);

			try (BufferedWriter wr = new BufferedWriter(new FileWriter("./data/" + annClass.fileName(), true));) {

				annFieldsMap.forEach((key, field) -> {
					try {
						CsvProperty annField = field.getAnnotation(CsvProperty.class);
						Object csvField = defineTypeField(someObject, field, annField, annObjects);

						wr.write(new StringBuilder().append(csvField).append(annClass.valuesSeparator()).toString());
					} catch (IOException | NoSuchFieldException | IllegalAccessException e) {
						LOGGER.log(Level.SEVERE, "Exception", e);
						throw new RuntimeException(e);
					}
				});
				wr.write(System.getProperty("line.separator"));
			}
		}
	}

	private void writeFieldName(List<Object> annObjects) throws IOException {

		for (Object someObject : annObjects) {

			CsvEntity annClass = someObject.getClass().getAnnotation(CsvEntity.class);
			Map<Integer, Field> annFieldsMap = findAnnFields(someObject);

			try (BufferedWriter wr = new BufferedWriter(new FileWriter("./data/" + annClass.fileName(), false));) {

				annFieldsMap.forEach((key, field) -> {
					try {
						List<String> lines = Files.readAllLines(Paths.get("./data/" + annClass.fileName()));
						if (!lines.contains(field.getName())) {
							wr.write(new StringBuilder(field.getName()).append(annClass.valuesSeparator()).toString());
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

	private Object defineTypeField(Object someObject, Field field, CsvProperty annField, List<Object> annObjects)
			throws NoSuchFieldException, IllegalAccessException {

		Object csvField;

		if (annField.propertyType() == PropertyType.CompositeProperty) {
			Object compositeField = field.get(someObject);
			String keyFieldValue = null;

			for (Object relatedObj : annObjects) {
				if (relatedObj.equals(compositeField)) {
					Field relatedObjField = relatedObj.getClass().getDeclaredField(annField.keyField());
					relatedObjField.setAccessible(true);
					keyFieldValue = relatedObjField.get(relatedObj).toString();
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
		Map<Integer, Field> annFieldsMap = new HashMap<Integer, Field>();

		for (Field field : fields) {
			field.setAccessible(true);
			if (field.isAnnotationPresent(CsvProperty.class)) {
				annFieldsMap.put(field.getAnnotation(CsvProperty.class).columnNumber(), field);
			}
		}
		return annFieldsMap;
	}
}
