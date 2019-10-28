package by.senla.cvs.module.processor;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Сonverting {

	private static final Logger LOGGER = Logger.getLogger(Сonverting.class.getName());

	public static Object convertField(String fieldType, String fieldValue) {

		Object convertedValue;
		try {
			switch (fieldType) {
			case "String":
				convertedValue = fieldValue;
				break;
			case "Integer":
			case "int":
				convertedValue = Integer.parseInt(fieldValue);
				break;
			case "Double":
			case "double":
				convertedValue = Double.parseDouble(fieldValue);
				break;
			case "Long":
			case "long":
				convertedValue = Long.parseLong(fieldValue);
				break;
			case "Boolean":
			case "boolean":
				convertedValue = Boolean.parseBoolean(fieldValue);
				break;
			case "Short":
			case "short":
				convertedValue = Short.parseShort(fieldValue);
				break;
			case "Float":
			case "float":
				convertedValue = Float.parseFloat(fieldValue);
				break;
			case "Character":
			case "char":
				convertedValue = fieldValue.charAt(0);
				break;
			case "Byte":
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
}
