package by.senla.cvs.module.processor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import by.senla.cvs.module.annotations.CsvProperty;
import by.senla.cvs.module.enums.PropertyType;

public class Parsing {

	public static List<Object> parseToEntity(Map<String, List<String[]>> strObjMap) {

		List<Object> newObjList = new ArrayList<>();

		strObjMap.forEach((key, strList) -> {

			String[] title = strList.get(0);
			strList.remove(0);

			for (String[] fieldsArray : strList) {

				try {
					Object someObject = createObject(key, title, fieldsArray, strObjMap, newObjList);
					if (!newObjList.contains(someObject)) {
						newObjList.add(someObject);
						// System.out.println("ADD" + someObject);
					}

				} catch (ClassNotFoundException | IllegalArgumentException | InstantiationException
						| IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		});
		return newObjList;
	}

	private static Object createObject(String key, String[] title, String[] fieldsArray,
			Map<String, List<String[]>> strObjMap, List<Object> newObjList)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		String className = key;
		Class<?> someObject = Class.forName(className);
		Field[] fields = someObject.getDeclaredFields();
		Object newObject = someObject.newInstance();

		for (Field field : fields) {
			field.setAccessible(true);
			if (field.isAnnotationPresent(CsvProperty.class)) {
				CsvProperty annField = field.getAnnotation(CsvProperty.class);
				setFieldValue(field, newObject, title, fieldsArray, annField, strObjMap, newObjList);
			}
		}
		// System.out.println("CREATE" + someObject);
		return newObject;
	}

	private static void setFieldValue(Field field, Object someObject, String[] title, String[] fieldsArray,
			CsvProperty annField, Map<String, List<String[]>> strObjMap, List<Object> newObjList)
			throws IllegalArgumentException, IllegalAccessException {

		String fieldType = field.getType().getSimpleName();
		String fieldValue = defineValueField(field, fieldsArray, title);
		Object convertedValue = null;

		if (annField.propertyType() == PropertyType.CompositeProperty) {
			setObjectField(field, fieldValue, someObject, annField, fieldsArray, strObjMap, title, newObjList);
		} else {
			convertedValue = Сonverting.convertField(fieldType, fieldValue);
		}
		field.set(someObject, convertedValue);
	}

	private static void setObjectField(Field field, String fieldValue, Object someObject, CsvProperty annField,
			String[] fieldsArray, Map<String, List<String[]>> strObjMap, String[] title, List<Object> newObjList) {

		String className = fieldValue.split("::", 2)[0];
		String keyField = fieldValue.split("::", 2)[1];
		int indexArray = Arrays.asList(title).indexOf(annField.keyField());

		strObjMap.forEach((key, strList) -> {
			for (String[] array : strList) {

				if (key.contains(className) & Arrays.asList(array).get(indexArray).equals(keyField)) {

					try {
						Object newObject = createObject(key, title, array, strObjMap, newObjList);

						if (!newObjList.contains(newObject)) {
							newObjList.add(newObject);
						}
						field.set(someObject, newObject);

					} catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
						throw new RuntimeException(e);
					}
				}
			}
		});
	}

	private static String defineValueField(Field field, String[] fieldsArray, String[] title) {
		String fieldValue = null;
		for (String fieldName : title) {
			if (field.getName().equals(fieldName)) {
				int indexFieldArray = Arrays.asList(title).indexOf(fieldName);
				fieldValue = fieldsArray[indexFieldArray];
			}
		}
		return fieldValue;
	}
}
