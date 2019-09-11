package by.senla.study.api.dao;

import javax.persistence.EntityManager;

import by.senla.study.model.entity.Category;

public interface ICategoryDao extends GenericDao<Category, Integer> {

	Category getFullInfo(Integer id, EntityManager entityManager);

}
