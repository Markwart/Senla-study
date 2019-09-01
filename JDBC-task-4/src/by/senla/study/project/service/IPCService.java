package by.senla.study.project.service;

import java.util.List;
import java.util.Map;

import by.senla.study.project.dao.jdbc.impl.entity.PC;

public interface IPCService {
	
	PC createEntity();

	PC get(Integer id);

	List<PC> getAll();

	void delete(Integer id);

	void save(Map<String, String> data);

	void update(Map<String, String> data, Integer id);

}
