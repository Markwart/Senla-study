package by.senla.study.api.dao;

import javax.persistence.EntityManager;

import by.senla.study.model.entity.Ranking;

public interface IRankingDao extends GenericDao<Ranking, Integer> {

	Ranking getFullInfo(Integer id, EntityManager entityManager);

}
