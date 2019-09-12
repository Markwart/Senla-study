package by.senla.study.api.dao;


import java.util.List;

import javax.persistence.EntityManager;

import by.senla.study.model.entity.Ad;

public interface IAdDao extends GenericDao<Ad, Integer> {

	List<Ad> searchByIndex(String text, EntityManager entityManager);

}
