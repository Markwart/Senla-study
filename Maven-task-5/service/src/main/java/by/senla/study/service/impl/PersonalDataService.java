package by.senla.study.service.impl;

import java.util.Date;

import by.senla.study.api.dao.IPersonalDataDao;
import by.senla.study.api.service.IPersonalDataService;
import by.senla.study.dao.impl.PersonalDataDao;
import by.senla.study.model.entity.PersonalData;
import by.senla.study.model.enums.Roles;

public class PersonalDataService extends AbstractService<PersonalData, Integer> implements IPersonalDataService {

	private static IPersonalDataDao personalDataDao = PersonalDataDao.getInstance();
	private static PersonalDataService instance;

	private PersonalDataService() {
		super(PersonalData.class, personalDataDao);
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
	public PersonalData updateOperation(PersonalData entity) {
		entity.setUpdated(new Date());
		return entity;
	}

	@Override
	public PersonalData insertOperation(PersonalData entity) {
		entity.setRole(Roles.USER);
		entity.setCreated(new Date());
		return entity;
	}

	@Override
	public Integer getPK(PersonalData entity) {
		return entity.getId();
	}

	@Override
	public PersonalData mergeOperation(PersonalData entity) {
		entity.setUpdated(new Date());
		return entity;
	}

}
