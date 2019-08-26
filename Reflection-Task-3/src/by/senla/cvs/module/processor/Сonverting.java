package by.senla.cvs.module.processor;

public class Сonverting {

	public static Object convertField(String fieldType, String fieldValue) {
		Object convertedValue;
		try {
			switch (fieldType) {
			case "int":
				convertedValue = Integer.parseInt(fieldValue);
				break;
			case "String":
				convertedValue = fieldValue;
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
				convertedValue = fieldValue.chars();
				break;
			case "byte":
				convertedValue = Byte.parseByte(fieldValue);
				break;
			default:
				convertedValue = fieldValue;
				break;
			}
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(e);
		}
		return convertedValue;
	}

}
