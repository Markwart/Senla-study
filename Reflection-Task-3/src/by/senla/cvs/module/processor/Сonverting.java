package by.senla.cvs.module.processor;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Сonverting {

	private static final Logger LOGGER = Logger.getLogger(Сonverting.class.getName());

	public static Object convertPrimitiveField(String fieldType, String fieldValue) {

		Object convertedValue;
		try {
			switch (fieldType) {
			case "int":
				convertedValue = Integer.parseInt(fieldValue);
				break;
			case "double":
				convertedValue = Double.parseDouble(fieldValue);
				break;
			case "long":
				convertedValue = Long.parseLong(fieldValue);
				break;
			case "boolean":
				convertedValue = Boolean.parseBoolean(fieldValue);
				break;
			case "short":
				convertedValue = Short.parseShort(fieldValue);
				break;
			case "float":
				convertedValue = Float.parseFloat(fieldValue);
				break;
			case "char":
				convertedValue = fieldValue.charAt(0);
				break;
			case "byte":
				convertedValue = Byte.parseByte(fieldValue);
				break;
			default:
				convertedValue = fieldValue;
				break;
			}
		} catch (IllegalArgumentException e) {
			LOGGER.log(Level.SEVERE, "Exception", e);
			throw new RuntimeException(e);
		}
		return convertedValue;
	}

	public static Object convertWrapperField(String fieldType, String fieldValue) {

		Object convertedValue;
		try {
			switch (fieldType) {
			case "Integer":
				convertedValue = Integer.parseInt(fieldValue);
				break;
			case "String":
				convertedValue = fieldValue;
				break;
			case "Double":
				convertedValue = Double.parseDouble(fieldValue);
				break;
			case "Long":
				convertedValue = Long.parseLong(fieldValue);
				break;
			case "Boolean":
				convertedValue = Boolean.parseBoolean(fieldValue);
				break;
			case "Short":
				convertedValue = Short.parseShort(fieldValue);
				break;
			case "Float":
				convertedValue = Float.parseFloat(fieldValue);
				break;
			case "Character":
				convertedValue = fieldValue.charAt(0);
				break;
			case "Byte":
				convertedValue = Byte.parseByte(fieldValue);
				break;
			default:
				convertedValue = fieldValue;
				break;
			}
		} catch (IllegalArgumentException e) {
			LOGGER.log(Level.SEVERE, "Exception", e);
			throw new RuntimeException(e);
		}
		return convertedValue;
	}

}
