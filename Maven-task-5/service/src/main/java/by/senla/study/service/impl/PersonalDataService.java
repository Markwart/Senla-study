package by.senla.study.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.senla.study.api.dao.IPersonalDataDao;
import by.senla.study.api.service.IPersonalDataService;
import by.senla.study.dao.impl.PersonalDataDao;
import by.senla.study.model.entity.PersonalData;

public class PersonalDataService implements IPersonalDataService {

	private static final Logger LOGGER = LogManager.getLogger(PersonalDataService.class);
	private IPersonalDataDao dao = PersonalDataDao.getInstance();
	private static PersonalDataService instance;

	private PersonalDataService() {
	}

	public static PersonalDataService getInstance() {
		if (instance == null) {
			instance = new PersonalDataService();
		}
		return instance;
	}

	public PersonalData createEntity() {
		return new PersonalData();
	}

	public PersonalData get(Integer id) {
		PersonalData entity = dao.get(id);
		return entity;
	}

	public void update(PersonalData entity) {
		dao.update(entity);
	}

	public void insert(PersonalData entity) {
		dao.insert(entity);
	}

	public void delete(Integer id) {
		dao.delete(id);
	}

	public List<PersonalData> selectAll() {
		List<PersonalData> all = dao.selectAll();
		return all;
	}

}
