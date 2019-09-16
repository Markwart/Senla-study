package by.senla.study.service.impl;

import org.springframework.stereotype.Service;

import by.senla.study.api.dao.IPersonalDataDao;
import by.senla.study.api.service.IPersonalDataService;
import by.senla.study.model.entity.PersonalData;

@Service
public class PersonalDataService extends AbstractService<PersonalData, Integer> implements IPersonalDataService {

	private static IPersonalDataDao personalDataDao;

	private PersonalDataService() {
		super(PersonalData.class, personalDataDao);
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
