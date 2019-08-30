package by.senla.task.four.dao.api.table;

import java.math.BigDecimal;

import by.senla.task.four.dao.jdbc.impl.entity.Product;

public interface IBaseEntity {

	Integer getId();
	void setId(Integer id);

	BigDecimal getPrice();
	void setPrice(BigDecimal price);

	Product getModel();
	void setModel(Product model);

}
