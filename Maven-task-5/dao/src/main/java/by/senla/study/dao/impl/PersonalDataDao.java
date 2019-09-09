package by.senla.study.dao.impl;

import by.senla.study.api.dao.IPersonalDataDao;
import by.senla.study.model.entity.PersonalData;

public class PersonalDataDao extends AbstractDao<PersonalData, Integer> implements IPersonalDataDao {

	private static PersonalDataDao instance;

	private PersonalDataDao() {
		super(PersonalData.class);
	}

	public static PersonalDataDao getInstance() {
		if (instance == null) {
			instance = new PersonalDataDao();
		}
		return instance;
	}
}
