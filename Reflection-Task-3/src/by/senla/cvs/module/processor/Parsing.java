package by.senla.cvs.module.processor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.senla.cvs.module.annotations.CsvProperty;
import by.senla.cvs.module.enums.PropertyType;

public class Parsing {

	public static List<Object> parseToEntity(Map<String, List<String[]>> strObjMap) {

		List<Object> newObjList = new ArrayList<>();

		Map<String, String[]> titleMap = createTitleMap(strObjMap);

		strObjMap.forEach((key, strList) -> {

			// String[] title = null;
			// String[] title = strList.get(0);
			// strList.remove(0);

			for (String[] fieldsArray : strList) {

				try {
					Object someObject = createObject(key, titleMap, fieldsArray, strObjMap, newObjList, strList);
					if (!newObjList.contains(someObject)) {
						newObjList.add(someObject);
					}
				} catch (ClassNotFoundException | IllegalArgumentException | InstantiationException
						| IllegalAccessException e) {
					throw new RuntimeException(e);
				}
			}
		});
		return newObjList;
	}

	private static Map<String, String[]> createTitleMap(Map<String, List<String[]>> strObjMap) {
		Map<String, String[]> titleMap = new HashMap<String, String[]>();

		strObjMap.forEach((key, strList) -> {
			titleMap.put(key, strList.get(0));
			strList.remove(0);
		});
		return titleMap;
	}

	private static Object createObject(String key, Map<String, String[]> titleMap, String[] fieldsArray,
			Map<String, List<String[]>> strObjMap, List<Object> newObjList, List<String[]> strList)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {

		String className = key;
		Class<?> someObject = Class.forName(className);
		Field[] fields = someObject.getDeclaredFields();

		Object newObject = someObject.newInstance();

		for (Field field : fields) {

			/*
			 * if (Arrays.asList(fieldsArray).contains(field.getName())) {
			 * 
			 * titleMap = fieldsArray; fieldsArray =
			 * strList.get(strList.indexOf(fieldsArray) + 1);
			 * strList.remove(strList.indexOf(fieldsArray)); }
			 * System.out.println(Arrays.toString(title) + " " +
			 * Arrays.toString(fieldsArray));
			 */

			field.setAccessible(true);
			if (field.isAnnotationPresent(CsvProperty.class)) {
				CsvProperty annField = field.getAnnotation(CsvProperty.class);
				setFieldValue(field, newObject, titleMap, fieldsArray, annField, strObjMap, newObjList);
			}

		}
		return newObject;
	}

	private static void setFieldValue(Field field, Object newObject, Map<String, String[]> titleMap,
			String[] fieldsArray, CsvProperty annField, Map<String, List<String[]>> strObjMap, List<Object> newObjList)
			throws IllegalArgumentException, IllegalAccessException {

		String fieldType = field.getType().getSimpleName();
		String fieldValue = defineValueField(field, fieldsArray, titleMap, newObject);
		Object convertedValue = null;

		if (annField.propertyType() == PropertyType.CompositeProperty) {
			setObjectField(field, fieldValue, newObject, annField, fieldsArray, strObjMap, titleMap, newObjList);
		} else {
			convertedValue = Сonverting.convertField(fieldType, fieldValue);
		}
		field.set(newObject, convertedValue);
	}

	private static void setObjectField(Field field, String fieldValue, Object newObject, CsvProperty annField,
			String[] fieldsArray, Map<String, List<String[]>> strObjMap, Map<String, String[]> titleMap,
			List<Object> newObjList) {

		String className = fieldValue.split("::", 2)[0];
		String keyField = fieldValue.split("::", 2)[1];
		// int indexArray = Arrays.asList(titleMap).indexOf(annField.keyField());

		Map<String, String[]> oneTitle = mapWithOneTitle(titleMap, newObject);
		int indexArray = Arrays.asList(oneTitle.get("title")).indexOf(annField.keyField());

		strObjMap.forEach((key, strList) -> {
			for (String[] array : strList) {

				if (key.contains(className) & Arrays.asList(array).get(indexArray).equals(keyField)) {

					try {
						Object relatedObject = createObject(key, titleMap, array, strObjMap, newObjList, strList);

						if (!newObjList.contains(relatedObject)) {
							newObjList.add(relatedObject);
						}
						field.set(newObject, relatedObject);

					} catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
						throw new RuntimeException(e);
					}
				}
			}
		});
	}

	private static String defineValueField(Field field, String[] fieldsArray, Map<String, String[]> titleMap,
			Object newObject) {
		String fieldValue = null;
		Map<String, String[]> oneTitle = mapWithOneTitle(titleMap, newObject);

		for (String fieldName : oneTitle.get("title")) {
			if (field.getName().equals(fieldName)) {
				int indexFieldArray = Arrays.asList(oneTitle.get("title")).indexOf(fieldName);
				fieldValue = fieldsArray[indexFieldArray];
			}
		}
		return fieldValue;
	}

	private static Map<String, String[]> mapWithOneTitle(Map<String, String[]> titleMap, Object newObject) {
		Map<String, String[]> oneTitle = null;
		titleMap.forEach((key, titleArray) -> {
			if (key.equals(newObject.getClass().getName())) {
				oneTitle.put("title", titleArray);
			}
		});
		return oneTitle;
	}
}
