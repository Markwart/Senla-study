package by.senla.study.project.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import by.senla.study.project.dao.IPrinterDao;
import by.senla.study.project.dao.IProductDao;
import by.senla.study.project.dao.jdbc.impl.PrinterDao;
import by.senla.study.project.dao.jdbc.impl.ProductDao;
import by.senla.study.project.dao.jdbc.impl.entity.Printer;
import by.senla.study.project.service.IPrinterService;

public class PrinterService implements IPrinterService {

	private IPrinterDao dao = PrinterDao.getInstance();
	private IProductDao daoProduct = ProductDao.getInstance();
	
	private static PrinterService instance;

	private PrinterService() {
	}

	public static PrinterService getInstance() {
		if (instance == null) {
			instance = new PrinterService();
		}
		return instance;
	}

	@Override
	public Printer createEntity() {
		return new Printer();
	}

	@Override
	public Printer get(Integer id) {
		Printer entity = dao.get(id);
		return entity;
	}

	@Override
	public List<Printer> getAll() {
		List<Printer> all = dao.selectAll();
		return all;
	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);
	}

	@Override
	public void save(Map<String, String> data) {
		Printer printer = createEntity();
		printer.setType(data.get("type"));
		printer.setColor(data.get("color").charAt(0));
		printer.setPrice(new BigDecimal(data.get("price")));
		printer.setModel(daoProduct.get(data.get("model")));
		dao.insert(printer);
	}

	@Override
	public void update(Map<String, String> data, Integer id) {
		Printer printer = dao.get(id);
		if (data.containsKey("type")) {
			printer.setType(data.get("type"));
		}
		if (data.containsKey("color")) {
			printer.setColor(data.get("color").charAt(0));
		}
		if (data.containsKey("price")) {
			printer.setPrice(new BigDecimal(data.get("price")));
		}
		if (data.containsKey("model")) {
			printer.setModel(daoProduct.get(data.get("model")));
		}
		dao.update(printer);
	}
}
