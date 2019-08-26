package by.senla.cvs.module.init;

import by.senla.cvs.module.annotations.CsvEntity;
import by.senla.cvs.module.annotations.CsvProperty;
import by.senla.cvs.module.enums.PropertyType;

@CsvEntity(fileName = "by.senla.cvs.module.init.ClassOne.csv")
public class ClassOne {

	@CsvProperty(columnNumber = 0, propertyType = PropertyType.SimpleProperty)
	private int id;

	@CsvProperty(columnNumber = 2, propertyType = PropertyType.SimpleProperty)
	private String someField;

	@CsvProperty(columnNumber = 1, propertyType = PropertyType.SimpleProperty)
	private int someInt;

	//@CsvProperty(columnNumber = 3, propertyType = PropertyType.CompositeProperty, keyField = "id")
	private ClassTwo someObject;

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

	public ClassTwo getSomeObject() {
		return someObject;
	}

	public void setSomeObject(ClassTwo someObject) {
		this.someObject = someObject;
	}
}