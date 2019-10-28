package by.senla.study.board.api.dao;

import by.senla.study.board.model.entity.PersonalData;

public interface IPersonalDataDao extends GenericDao<PersonalData, Integer> {

	PersonalData getUserByLogin(String login);

}
