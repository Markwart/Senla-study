package by.senla.task.four.dao.jdbc.impl.entity;

import by.senla.task.four.dao.api.table.IProduct;

public class Product implements IProduct {

	private String model;
	private String maker;
	private String type;

	@Override
	public String getModel() {
		return model;
	}

	@Override
	public void setModel(String model) {
		this.model = model;
	}

	@Override
	public String getMaker() {
		return maker;
	}

	@Override
	public void setMaker(String maker) {
		this.maker = maker;
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
