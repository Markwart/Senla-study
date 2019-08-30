package by.senla.task.four.dao.jdbc.impl.entity;

import by.senla.task.four.dao.api.table.IPrinter;

public class Printer extends BaseEntity implements IPrinter {

	private Character color;
	private String type;

	@Override
	public Character getColor() {
		return color;
	}

	@Override
	public void setColor(Character color) {
		this.color = color;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}
}
