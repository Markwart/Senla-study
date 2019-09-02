package by.senla.study.project.entity;

import java.math.BigDecimal;

public class BaseEntity {

	private Integer id;
	private Product model;
	private BigDecimal price;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Product getModel() {
		return model;
	}

	public void setModel(Product model) {
		this.model = model;
	}

}
