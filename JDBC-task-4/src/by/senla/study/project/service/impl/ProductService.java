package by.senla.study.project.service.impl;

import java.util.List;
import java.util.Map;

import by.senla.study.project.dao.IProductDao;
import by.senla.study.project.dao.jdbc.impl.ProductDao;
import by.senla.study.project.dao.jdbc.impl.entity.Product;
import by.senla.study.project.service.IProductService;

public class ProductService implements IProductService {

	private IProductDao dao = ProductDao.getInstance();
	
	private static ProductService instance;

	private ProductService() {
	}

	public static ProductService getInstance() {
		if (instance == null) {
			instance = new ProductService();
		}
		return instance;
	}

	@Override
	public Product createEntity() {
		return new Product();
	}

	@Override
	public Product get(String model) {
		Product entity = dao.get(model);
		return entity;
	}

	@Override
	public List<Product> getAll() {
		List<Product> all = dao.selectAll();
		return all;
	}

	@Override
	public void delete(String model) {
		dao.delete(model);
	}

	@Override
	public void save(Map<String, String> data) {
		Product product = createEntity();
		product.setMaker(data.get("maker"));
		product.setType(data.get("type"));
		product.setModel(data.get("model"));
		dao.insert(product);
	}

	@Override
	public void update(Map<String, String> data, String model) {
		Product product = dao.get(model);
		if (data.containsKey("maker")) {
			product.setMaker(data.get("maker"));
		}
		if (data.containsKey("type")) {
			product.setType(data.get("type"));
		}
		dao.update(product);
	}
}
