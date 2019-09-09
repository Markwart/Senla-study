package by.senla.study.service.impl;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.senla.study.api.dao.IPersonalDataDao;
import by.senla.study.api.service.IPersonalDataService;
import by.senla.study.dao.impl.PersonalDataDao;
import by.senla.study.model.entity.PersonalData;

public class PersonalDataService extends BaseService implements IPersonalDataService {

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

	@Override
	public PersonalData createEntity() {
		return new PersonalData();
	}

	@Override
	public PersonalData get(Integer id) {
		PersonalData entity = dao.get(id, entityManager);
		return entity;
	}

	@Override
	public void update(PersonalData entity) {
		dao.update(entity, entityManager);
		LOGGER.log(Level.INFO, String.format("personal data with id=%s was updated", entity.getId()));
	}

	@Override
	public void insert(PersonalData entity) {
		dao.insert(entity, entityManager);
		LOGGER.log(Level.INFO, String.format("new personal data with id=%s was created", entity.getId()));
	}

	@Override

	public void delete(Integer id) {
		dao.delete(id, entityManager);
		LOGGER.log(Level.INFO, String.format("personal data with id=%s was deleted", id));
	}

	@Override
	public List<PersonalData> selectAll() {
		List<PersonalData> all = dao.selectAll(entityManager);
		return all;
	}

}
