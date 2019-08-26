package by.senla.cvs.module.init;

import by.senla.cvs.module.annotations.CsvEntity;
import by.senla.cvs.module.annotations.CsvProperty;
import by.senla.cvs.module.enums.PropertyType;

@CsvEntity(fileName = "by.senla.cvs.module.init.ClassOne.csv")
public class ClassOne {

	@CsvProperty(columnNumber = 0, propertyType = PropertyType.SimpleProperty)
	private int id;

	@CsvProperty(columnNumber = 2, propertyType = PropertyType.SimpleProperty)
	private String name;

	@CsvProperty(columnNumber = 1, propertyType = PropertyType.SimpleProperty)
	private int number;

	@CsvProperty(columnNumber = 3, propertyType = PropertyType.CompositeProperty, keyField = "id")
	private ClassTwo someObject;

	public String getSomeField() {
		return name;
	}

	public void setSomeField(String name) {
		this.name = name;
	}

	public int getSomeInt() {
		return number;
	}

	public void setSomeInt(int number) {
		this.number = number;
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