package by.senla.cvs.module.init;

import by.senla.cvs.module.annotations.CsvProperty;
import by.senla.cvs.module.enums.PropertyType;

public class ClassZero {

	@CsvProperty(columnNumber = 0, propertyType = PropertyType.SIMPLE)
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
