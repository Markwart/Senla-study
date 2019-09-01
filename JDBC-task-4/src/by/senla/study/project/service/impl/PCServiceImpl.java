package by.senla.study.project.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import by.senla.study.project.dao.jdbc.impl.PCDaoImpl;
import by.senla.study.project.dao.jdbc.impl.ProductDaoImpl;
import by.senla.study.project.dao.jdbc.impl.entity.PC;
import by.senla.study.project.service.IPCService;

public class PCServiceImpl implements IPCService {

	private static PCServiceImpl instance;

	private PCServiceImpl() {
	}

	public static PCServiceImpl getInstance() {
		if (instance != null) {
			instance = new PCServiceImpl();
		}
		return instance;
	}

	PCDaoImpl dao = PCDaoImpl.getInstance();
	ProductDaoImpl daoProduct = ProductDaoImpl.getInstance();

	@Override
	public PC createEntity() {
		return new PC();
	}

	@Override
	public PC get(Integer id) {
		PC entity = dao.get(id);
		return entity;
	}

	@Override
	public List<PC> getAll() {
		List<PC> all = new ArrayList<>();
		return all;
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public void save(Map<String, String> data) {
		PC pc = createEntity();
		pc.setHd(Integer.parseInt(data.get("hd")));
		pc.setRam(Integer.parseInt(data.get("ram")));
		pc.setSpeed(Integer.parseInt(data.get("speed")));
		pc.setCd(data.get("cd"));
		pc.setPrice(new BigDecimal(data.get("price")));
		pc.setModel(daoProduct.get(data.get("model")));
		dao.insert(pc);
	}

	@Override
	public void update(Map<String, String> data, Integer id) {
		PC pc = dao.get(id);
		if (data.containsKey("hd")) {
			pc.setHd(Integer.parseInt(data.get("hd")));
		}
		if (data.containsKey("ram")) {
			pc.setRam(Integer.parseInt(data.get("ram")));
		}
		if (data.containsKey("speed")) {
			pc.setSpeed(Integer.parseInt(data.get("speed")));
		}
		if (data.containsKey("cd")) {
			pc.setCd(data.get("cd"));
		}
		if (data.containsKey("price")) {
			pc.setPrice(new BigDecimal(data.get("price")));
		}
		if (data.containsKey("model")) {
			pc.setModel(daoProduct.get(data.get("model")));
		}
		dao.update(pc);
	}
}
