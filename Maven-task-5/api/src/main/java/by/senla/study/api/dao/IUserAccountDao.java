package by.senla.study.api.dao;

import javax.persistence.EntityManager;

import by.senla.study.model.entity.UserAccount;

public interface IUserAccountDao extends GenericDao<UserAccount, Integer> {

	UserAccount getFullInfo(Integer id, EntityManager entityManager);

}
