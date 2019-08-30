package by.senla.task.four.dao.api;

import by.senla.task.four.dao.api.table.IProduct;

public interface IProductDao extends IDao<IProduct, Integer> {

	IProduct get(String model);

	void delete(String model);

}
