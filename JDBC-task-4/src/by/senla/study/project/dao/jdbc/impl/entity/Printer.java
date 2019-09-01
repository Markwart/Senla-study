package by.senla.study.project.dao.jdbc.impl.entity;

public class Printer extends BaseEntity {

	private Character color;
	private String type;

	public Character getColor() {
		return color;
	}

	public void setColor(Character color) {
		this.color = color;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
