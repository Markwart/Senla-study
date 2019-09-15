package by.senla.study.service.impl;

import by.senla.study.api.dao.IPersonalDataDao;
import by.senla.study.api.service.IPersonalDataService;
import by.senla.study.dao.impl.PersonalDataDao;
import by.senla.study.model.entity.PersonalData;

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
	public Integer getPK(PersonalData entity) {
		return entity.getId();
	}
}
