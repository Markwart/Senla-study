package by.senla.cvs.module.init;

import by.senla.cvs.module.annotations.CsvEntity;
import by.senla.cvs.module.annotations.CsvProperty;
import by.senla.cvs.module.enums.PropertyType;

@CsvEntity(fileName = "by.senla.cvs.module.init.ClassTwo.csv")
public class ClassTwo {

	@CsvProperty(columnNumber = 0, propertyType = PropertyType.SimpleProperty)
	private int id;

	@CsvProperty(columnNumber = 1, propertyType = PropertyType.SimpleProperty)
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
