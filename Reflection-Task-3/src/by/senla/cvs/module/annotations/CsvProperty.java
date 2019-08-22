package by.senla.cvs.module.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import by.senla.cvs.module.enums.PropertyType;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface CsvProperty {

	public PropertyType propertyType();

	public int columnNumber();

	public int keyField();

}
