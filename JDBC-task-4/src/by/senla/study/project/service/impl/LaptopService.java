package by.senla.study.project.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import by.senla.study.project.dao.ILaptopDao;
import by.senla.study.project.dao.IProductDao;
import by.senla.study.project.dao.jdbc.impl.LaptopDao;
import by.senla.study.project.dao.jdbc.impl.ProductDao;
import by.senla.study.project.dao.jdbc.impl.entity.Laptop;
import by.senla.study.project.service.ILaptopService;

public class LaptopService implements ILaptopService {

	private ILaptopDao dao = LaptopDao.getInstance();
	private IProductDao daoProduct = ProductDao.getInstance();
	
	private static LaptopService instance;

	private LaptopService() {
	}

	public static LaptopService getInstance() {
		if (instance == null) {
			instance = new LaptopService();
		}
		return instance;
	}

	@Override
	public Laptop createEntity() {
		return new Laptop();
	}

	@Override
	public Laptop get(Integer id) {
		Laptop entity = dao.get(id);
		return entity;
	}

	@Override
	public List<Laptop> getAll() {
		List<Laptop> laptopList = dao.selectAll();
		return laptopList;
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public void save(Map<String, String> data) {
		Laptop laptop = createEntity();
		laptop.setHd(Integer.parseInt(data.get("hd")));
		laptop.setRam(Integer.parseInt(data.get("ram")));
		laptop.setSpeed(Integer.parseInt(data.get("speed")));
		laptop.setScreen(Integer.parseInt(data.get("screen")));
		laptop.setPrice(new BigDecimal(data.get("price")));
		laptop.setModel(daoProduct.get(data.get("model")));
		dao.insert(laptop);
	}

	@Override
	public void update(Map<String, String> data, Integer id) {
		Laptop laptop = dao.get(id);
		if (data.containsKey("hd")) {
			laptop.setHd(Integer.parseInt(data.get("hd")));
		}
		if (data.containsKey("ram")) {
			laptop.setRam(Integer.parseInt(data.get("ram")));
		}
		if (data.containsKey("speed")) {
			laptop.setSpeed(Integer.parseInt(data.get("speed")));
		}
		if (data.containsKey("screen")) {
			laptop.setScreen(Integer.parseInt(data.get("screen")));
		}
		if (data.containsKey("price")) {
			laptop.setPrice(new BigDecimal(data.get("price")));
		}
		if (data.containsKey("model")) {
			laptop.setModel(daoProduct.get(data.get("model")));
		}
		dao.update(laptop);
	}
}
