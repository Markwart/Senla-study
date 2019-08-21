package by.senla.cvs.module.init;

import by.senla.cvs.module.annotations.CsvEntity;
import by.senla.cvs.module.annotations.CsvProperty;
import by.senla.cvs.module.enums.PropertyType;

@CsvEntity(fileName = "ClassOne.csv")
public class ClassOne {

	@CsvProperty(columnNumber = 0, propertyType = PropertyType.SimpleProperty, keyField = 0)
	private int id;
	
	@CsvProperty(columnNumber = 0, propertyType = PropertyType.SimpleProperty, keyField = 0)
	private String someField;

	@CsvProperty(columnNumber = 0, propertyType = PropertyType.SimpleProperty, keyField = 0)
	private int someInt;

	public String getSomeField() {
		return someField;
	}

	public void setSomeField(String someField) {
		this.someField = someField;
	}

	public int getSomeInt() {
		return someInt;
	}

	public void setSomeInt(int someInt) {
		this.someInt = someInt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
