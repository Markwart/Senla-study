package by.senla.study.project.service;

import java.util.List;
import java.util.Map;

import by.senla.study.project.entity.Product;

public interface IProductService {

	Product createEntity();
	
	Product get(String model);
	
	List<Product> getAll();

	void delete(String model);

	void save(Map<String, String> data);

	void update(Map<String, String> data, String model);

	List<Product> getPrinterMakers();

	List<Product> getPCAndLaptopMakers(Integer speed);

}
