package by.senla.study.api.dao;

import javax.persistence.EntityManager;

import by.senla.study.model.entity.Ad;

public interface IAdDao extends IDao<Ad, Integer> {

	Ad getFullInfo(Integer id, EntityManager entityManager);

}
