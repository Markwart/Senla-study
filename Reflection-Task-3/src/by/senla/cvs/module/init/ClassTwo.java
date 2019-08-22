package by.senla.cvs.module.init;

import by.senla.cvs.module.annotations.CsvEntity;

@CsvEntity(fileName = "ClassTwo.csv")
public class ClassTwo {
	
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
