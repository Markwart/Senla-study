package by.senla.study.api.dao;

import javax.persistence.EntityManager;

import by.senla.study.model.entity.Message;

public interface IMessageDao extends GenericDao<Message, Integer> {

	Message getFullInfo(Integer id, EntityManager entityManager);

}
