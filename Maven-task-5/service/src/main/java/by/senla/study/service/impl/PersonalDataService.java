package by.senla.study.service.impl;

import java.util.List;

import by.senla.study.api.dao.IPersonalDataDao;
import by.senla.study.api.service.IPersonalDataService;
import by.senla.study.dao.impl.PersonalDataDao;
import by.senla.study.model.entity.PersonalData;

public class PersonalDataService extends AbstractService<PersonalData, Integer> implements IPersonalDataService {

	private IPersonalDataDao personalDataDao = PersonalDataDao.getInstance();
	private static PersonalDataService instance;

	private PersonalDataService() {
		super(PersonalData.class);
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
	public PersonalData getByID(Integer id) {
		PersonalData entity = personalDataDao.getByID(id, entityManager);
		return entity;
	}

	@Override
	public List<PersonalData> selectAll() {
		List<PersonalData> personalDataList = personalDataDao.selectAll(entityManager);
		return personalDataList;
	}

	@Override
	public void updateOperation(PersonalData entity) {
		personalDataDao.update(entity, entityManager);
	}

	@Override
	public void insertOperation(PersonalData entity) {
		personalDataDao.insert(entity, entityManager);
	}

	@Override
	public void deleteOperation(Integer id) {
		personalDataDao.deleteByID(id, entityManager);
	}

	@Override
	public Integer getPK(PersonalData entity) {
		return entity.getId();
	}

}
