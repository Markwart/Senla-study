package by.senla.study.api.dao;

import javax.persistence.EntityManager;

import by.senla.study.model.entity.UserAccount;

public interface IUserAccountDao extends IDao<UserAccount, Integer> {

	UserAccount getFullInfo(Integer id, EntityManager entityManager);

}
