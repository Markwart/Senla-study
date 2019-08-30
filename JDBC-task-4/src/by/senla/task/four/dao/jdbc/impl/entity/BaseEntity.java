package by.senla.task.four.dao.jdbc.impl.entity;

import java.math.BigDecimal;

import by.senla.task.four.dao.api.table.IBaseEntity;

public class BaseEntity implements IBaseEntity {

	private Integer id;
	private Product model;
	private BigDecimal price;

	@Override
	public Integer getId() {
		return id;
	}

	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public BigDecimal getPrice() {
		return price;
	}

	@Override
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public Product getModel() {
		return model;
	}

	@Override
	public void setModel(Product model) {
		this.model = model;
	}

}
