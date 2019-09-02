package by.senla.study.project.service;

import java.util.List;
import java.util.Map;

import by.senla.study.project.entity.Printer;

public interface IPrinterService {
	
	Printer createEntity();

	Printer get(Integer id);

	List<Printer> getAll();

	void delete(Integer id);

	void save(Map<String, String> data);

	void update(Map<String, String> data, Integer id);

}
