package by.senla.cvs.module.processor;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import by.senla.cvs.module.annotations.CsvEntity;
import by.senla.cvs.module.annotations.CsvProperty;
import by.senla.cvs.module.enums.PropertyType;

public class Parsing {

	private static final Logger LOGGER = Logger.getLogger(Parsing.class.getName());

	public List<Object> parseToEntity(Map<String, List<String[]>> strObjMap) {

		List<Object> newObjList = new ArrayList<>();
		Map<String, String[]> titleMap = createTitleMap(strObjMap);

		strObjMap.forEach((className, strList) -> {
			for (String[] fieldsArray : strList) {
				try {
					Object someObject = createObject(className, titleMap, fieldsArray, strObjMap, newObjList);

					someObject = checkExistenceObj(newObjList, someObject);
					if (!isExistObj(newObjList, someObject)) {
						newObjList.add(someObject);
					}
				} catch (ClassNotFoundException | IllegalArgumentException | InstantiationException
						| IllegalAccessException | NoSuchFieldException e) {
					LOGGER.log(Level.SEVERE, "Exception", e);
					throw new RuntimeException(e);
				}
			}
		});
		return newObjList;
	}

	private Map<String, String[]> createTitleMap(Map<String, List<String[]>> strObjMap) {
		Map<String, String[]> titleMap = new HashMap<String, String[]>();

		strObjMap.forEach((className, strList) -> {
			titleMap.put(className, strList.get(0));
			strList.remove(0);
		});
		return titleMap;
	}

	private Object createObject(String className, Map<String, String[]> titleMap, String[] fieldsArray,
			Map<String, List<String[]>> strObjMap, List<Object> newObjList)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {

		Class<?> someObject = Class.forName(className);
		Field[] fields = someObject.getDeclaredFields();
		Object newObject = someObject.newInstance();

		for (Field field : fields) {
			field.setAccessible(true);
			CsvProperty annField = field.getAnnotation(CsvProperty.class);
			if (field.isAnnotationPresent(CsvProperty.class)) {
				setFieldValue(field, newObject, titleMap, fieldsArray, annField, strObjMap, newObjList);
			}
		}
		return newObject;
	}

	private boolean isExistObj(List<Object> newObjList, Object someObject)
			throws IllegalAccessException, NoSuchFieldException {
		boolean existence = false;

		for (Object object : newObjList) {
			CsvEntity annClass = someObject.getClass().getAnnotation(CsvEntity.class);
			if (someObject.getClass().getName().equals(object.getClass().getName())) {

				Field someObjectField = someObject.getClass().getDeclaredField(annClass.entityId());
				Field objectField = object.getClass().getDeclaredField(annClass.entityId());
				someObjectField.setAccessible(true);
				objectField.setAccessible(true);

				if (someObjectField.get(someObject).equals(objectField.get(object))) {
					existence = true;
				} else {
					existence = false;
				}
			}
		}
		return existence;
	}

	private Object checkExistenceObj(List<Object> newObjList, Object someObject)
			throws IllegalAccessException, NoSuchFieldException {

		for (Object object : newObjList) {
			CsvEntity annClass = someObject.getClass().getAnnotation(CsvEntity.class);
			if (someObject.getClass().getName().equals(object.getClass().getName())) {

				Field someObjectField = someObject.getClass().getDeclaredField(annClass.entityId());
				Field objectField = object.getClass().getDeclaredField(annClass.entityId());
				someObjectField.setAccessible(true);
				objectField.setAccessible(true);

				if (someObjectField.get(someObject).equals(objectField.get(object))) {
					someObject = object;
				}
			}
		}
		return someObject;
	}

	private void setFieldValue(Field field, Object newObject, Map<String, String[]> titleMap, String[] fieldsArray,
			CsvProperty annField, Map<String, List<String[]>> strObjMap, List<Object> newObjList)
			throws IllegalArgumentException, IllegalAccessException {

		String fieldType = field.getType().getSimpleName();
		String fieldValue = defineValueField(field, fieldsArray, titleMap, newObject);

		if (annField.propertyType() == PropertyType.CompositeProperty) {
			setObjectField(field, fieldValue, newObject, annField, fieldsArray, strObjMap, titleMap, newObjList);
		} else {
			field.set(newObject, Сonverting.convertField(fieldType, fieldValue));
		}
	}

	private void setObjectField(Field field, String fieldValue, Object newObject, CsvProperty annField,
			String[] fieldsArray, Map<String, List<String[]>> strObjMap, Map<String, String[]> titleMap,
			List<Object> newObjList) {

		String className = fieldValue.split("::", 2)[0];
		String keyField = fieldValue.split("::", 2)[1];

		Map<String, String[]> oneTitle = mapWithOneTitle(titleMap, newObject);
		int indexArray = Arrays.asList(oneTitle.get("title")).indexOf(annField.keyField());

		strObjMap.forEach((key, strList) -> {
			for (String[] array : strList) {

				if (key.contains(className) & Arrays.asList(array).get(indexArray).equals(keyField)) {
					try {
						Object relatedObject = createObject(key, titleMap, array, strObjMap, newObjList);

						relatedObject = checkExistenceObj(newObjList, relatedObject);
						field.set(newObject, relatedObject);
						if (!isExistObj(newObjList, relatedObject)) {
							newObjList.add(relatedObject);
						}
					} catch (ClassNotFoundException | IllegalAccessException | InstantiationException
							| NoSuchFieldException e) {
						LOGGER.log(Level.SEVERE, "Exception", e);
						throw new RuntimeException(e);
					}
				}
			}
		});
	}

	private String defineValueField(Field field, String[] fieldsArray, Map<String, String[]> titleMap,
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

	private Map<String, String[]> mapWithOneTitle(Map<String, String[]> titleMap, Object newObject) {
		Map<String, String[]> oneTitle = new HashMap<>();

		titleMap.forEach((className, titleArray) -> {
			if (className.equals(newObject.getClass().getName())) {
				oneTitle.put("title", titleArray);
			}
		});
		return oneTitle;
	}
}
