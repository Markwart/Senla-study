package by.senla.study.project.dao;

import java.util.List;

import by.senla.study.project.entity.Product;

public interface IProductDao extends IDao<Product, String> {

	List<Product> findPrinterMakers();

	List<Product> findPCAndLaptopMakers(Integer speed);

}
