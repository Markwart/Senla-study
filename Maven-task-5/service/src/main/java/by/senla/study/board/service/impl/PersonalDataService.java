package by.senla.study.board.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.senla.study.board.api.dao.IPersonalDataDao;
import by.senla.study.board.api.service.IPersonalDataService;
import by.senla.study.board.model.entity.PersonalData;

@Service
@Transactional
public class PersonalDataService extends AbstractService<PersonalData, Integer> implements IPersonalDataService {
	
	private final Logger LOGGER = LogManager.getLogger(PersonalDataService.class);

	private final IPersonalDataDao personalDataDao;

	@Autowired
	public PersonalDataService(IPersonalDataDao personalDataDao) {
		super(PersonalData.class, personalDataDao);
		this.personalDataDao = personalDataDao;
	}
}
