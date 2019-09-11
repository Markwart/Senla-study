package by.senla.study.api.dao;

import javax.persistence.EntityManager;

import by.senla.study.model.entity.Chat;

public interface IChatDao extends GenericDao<Chat, Integer> {

	Chat getFullInfo(Integer id, EntityManager entityManager);

}
