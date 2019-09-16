package by.senla.study.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.senla.study.api.dao.IPersonalDataDao;
import by.senla.study.api.service.IPersonalDataService;
import by.senla.study.model.entity.PersonalData;

@Service
public class PersonalDataService extends AbstractService<PersonalData, Integer> implements IPersonalDataService {

	private final IPersonalDataDao personalDataDao;

	@Autowired
	public PersonalDataService(IPersonalDataDao personalDataDao) {
		super(PersonalData.class, personalDataDao);
		this.personalDataDao = personalDataDao;
	}
}
