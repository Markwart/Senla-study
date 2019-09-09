package by.senla.study.api.dao;

import javax.persistence.EntityManager;

import by.senla.study.model.entity.Category;

public interface ICategoryDao extends IDao<Category, Integer> {

	Category getFullInfo(Integer id, EntityManager entityManager);

}
