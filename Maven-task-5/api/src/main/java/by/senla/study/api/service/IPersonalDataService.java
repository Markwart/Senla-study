package by.senla.study.api.service;

import java.util.List;

import by.senla.study.model.entity.PersonalData;

public interface IPersonalDataService {

	PersonalData createEntity();

	PersonalData get(Integer id);
	
	void update(PersonalData entity);

	void insert(PersonalData entity);

	void delete(Integer id);
	
	List<PersonalData> selectAll();
}
