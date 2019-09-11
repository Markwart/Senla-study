package by.senla.cvs.module.init;

import by.senla.cvs.module.annotations.CsvEntity;
import by.senla.cvs.module.annotations.CsvProperty;
import by.senla.cvs.module.enums.PropertyType;

@CsvEntity(fileName = "ClassTwo.csv")
public class ClassTwo extends ClassZero {

	/*@CsvProperty(columnNumber = 0, propertyType = PropertyType.SIMPLE)
	private int id;*/

	@CsvProperty(columnNumber = 1, propertyType = PropertyType.SIMPLE)
	private String name;

	@CsvProperty(columnNumber = 2, propertyType = PropertyType.SIMPLE)
	private Integer number;
	
	@CsvProperty(columnNumber = 3, propertyType = PropertyType.SIMPLE)
	private Character character;
	
	@CsvProperty(columnNumber = 4, propertyType = PropertyType.SIMPLE)
	private Float floatNumber;

	/*public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}*/

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Character getCharacter() {
		return character;
	}

	public void setCharacter(Character character) {
		this.character = character;
	}

	public Float getFl() {
		return floatNumber;
	}

	public void setFl(Float floatNumber) {
		this.floatNumber = floatNumber;
	}

}
