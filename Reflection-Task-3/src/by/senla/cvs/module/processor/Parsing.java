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

	public List<Object> parseToEntity(Map<String, List<String[]>> strObjectsMap) {

		List<Object> newObjectsList = new ArrayList<>();
		Map<String, String[]> fieldsNameMap = createFieldsMap(strObjectsMap);
		Map<String, String> classNameMap = createClassNameMap(strObjectsMap);

		strObjectsMap.forEach((className, strList) -> {
			for (String[] fieldsArray : strList) {
				try {
					Object someObject = createObject(classNameMap, className, fieldsNameMap, fieldsArray, strObjectsMap,
							newObjectsList);

					someObject = checkExistenceObj(newObjectsList, someObject);
					if (!isExistObj(newObjectsList, someObject)) {
						newObjectsList.add(someObject);
					}
				} catch (ClassNotFoundException | IllegalArgumentException | InstantiationException
						| IllegalAccessException | NoSuchFieldException e) {
					LOGGER.log(Level.SEVERE, "Exception", e);
					throw new RuntimeException(e);
				}
			}
		});
		return newObjectsList;
	}

	private Map<String, String[]> createFieldsMap(Map<String, List<String[]>> strObjectsMap) {
		Map<String, String[]> fieldsNameMap = new HashMap<String, String[]>();

		strObjectsMap.forEach((className, strList) -> {
			fieldsNameMap.put(className, strList.get(0));
			strList.remove(0);
		});
		return fieldsNameMap;
	}

	private Map<String, String> createClassNameMap(Map<String, List<String[]>> strObjectsMap) {
		Map<String, String> classNameMap = new HashMap<String, String>();

		strObjectsMap.forEach((className, strList) -> {
			classNameMap.put(className, Arrays.asList(strList.get(strList.size() - 1)).get(0));
			strList.remove(strList.size() - 1);
		});
		return classNameMap;
	}

	private Object createObject(Map<String, String> classNameMap, String className, Map<String, String[]> fieldsNameMap,
			String[] fieldsArray, Map<String, List<String[]>> strObjectsMap, List<Object> newObjectsList)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {

		Class<?> someObject = Class.forName(classNameMap.get(className));
		Field[] fields = someObject.getDeclaredFields();
		Object newObject = someObject.newInstance();

		for (Field field : fields) {
			field.setAccessible(true);
			CsvProperty annotatedField = field.getAnnotation(CsvProperty.class);
			if (field.isAnnotationPresent(CsvProperty.class)) {
				setFieldValue(classNameMap, field, newObject, fieldsNameMap, fieldsArray, annotatedField, strObjectsMap,
						newObjectsList);
			}
		}
		return newObject;
	}

	private boolean isExistObj(List<Object> newObjectsList, Object someObject)
			throws IllegalAccessException, NoSuchFieldException {
		boolean existence = false;

		for (Object object : newObjectsList) {
			CsvEntity annotatedClass = someObject.getClass().getAnnotation(CsvEntity.class);
			if (someObject.getClass().getName().equals(object.getClass().getName())) {

				Field someObjectField = someObject.getClass().getDeclaredField(annotatedClass.entityId());
				Field objectField = object.getClass().getDeclaredField(annotatedClass.entityId());
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

	private Object checkExistenceObj(List<Object> newObjectsList, Object someObject)
			throws IllegalAccessException, NoSuchFieldException {

		for (Object object : newObjectsList) {
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

	private void setFieldValue(Map<String, String> classNameMap, Field field, Object newObject,
			Map<String, String[]> fieldsNameMap, String[] fieldsArray, CsvProperty annotatedField,
			Map<String, List<String[]>> strObjectsMap, List<Object> newObjectsList)
			throws IllegalArgumentException, IllegalAccessException {

		String fieldType = field.getType().getSimpleName();
		String fieldValue = defineValueField(field, fieldsArray, fieldsNameMap, newObject);

		if (annotatedField.propertyType() == PropertyType.CompositeProperty) {
			setObjectField(classNameMap, field, fieldValue, newObject, annotatedField, fieldsArray, strObjectsMap,
					fieldsNameMap, newObjectsList);
		} else {
			Object convertedField = (fieldType.substring(0, 1).matches("[A-Z]"))
					? Сonverting.convertWrapperField(fieldType, fieldValue)
					: Сonverting.convertPrimitiveField(fieldType, fieldValue);
			field.set(newObject, convertedField);
		}
	}

	private void setObjectField(Map<String, String> classNameMap, Field field, String fieldValue, Object newObject,
			CsvProperty annotatedField, String[] fieldsArray, Map<String, List<String[]>> strObjectsMap,
			Map<String, String[]> fieldsNameMap, List<Object> newObjectsList) {

		String className = fieldValue.split("::", 2)[0];
		String keyField = fieldValue.split("::", 2)[1];

		int indexArray = Arrays.asList(fieldsNameMap.get(className)).indexOf(annotatedField.keyField());

		strObjectsMap.forEach((key, strList) -> {
			for (String[] array : strList) {

				if (key.contains(className) & Arrays.asList(array).get(indexArray).equals(keyField)) {
					try {
						Object relatedObject = createObject(classNameMap, key, fieldsNameMap, array, strObjectsMap,
								newObjectsList);

						relatedObject = checkExistenceObj(newObjectsList, relatedObject);
						field.set(newObject, relatedObject);
						if (!isExistObj(newObjectsList, relatedObject)) {
							newObjectsList.add(relatedObject);
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

	private String defineValueField(Field field, String[] fieldsArray, Map<String, String[]> fieldsNameMap,
			Object newObject) {
		String fieldValue = null;
		String className = newObject.getClass().getSimpleName();

		for (String fieldName : fieldsNameMap.get(className)) {
			if (field.getName().equals(fieldName)) {
				int indexFieldArray = Arrays.asList(fieldsNameMap.get(newObject.getClass().getSimpleName()))
						.indexOf(fieldName);
				fieldValue = fieldsArray[indexFieldArray];
			}
		}
		return fieldValue;
	}
}
