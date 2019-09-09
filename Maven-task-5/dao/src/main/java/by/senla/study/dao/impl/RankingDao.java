package by.senla.study.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import by.senla.study.api.dao.IRankingDao;
import by.senla.study.model.entity.Ranking;

public class RankingDao extends AbstractDao<Ranking, Integer> implements IRankingDao {

	private static RankingDao instance;

	private RankingDao() {
		super(Ranking.class);
	}

	public static RankingDao getInstance() {
		if (instance == null) {
			instance = new RankingDao();
		}
		return instance;
	}
	
	@Override
	public Ranking getFullInfo(Integer id, EntityManager entityManager) {
		EntityManager em = entityManager;
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Ranking> cq = cb.createQuery(Ranking.class);
		Root<Ranking> from = cq.from(Ranking.class);

		cq.select(from);
		from.fetch("userFrom", JoinType.LEFT);
		from.fetch("userWhom", JoinType.LEFT);

		cq.where(cb.equal(from.get("id"), id));
		TypedQuery<Ranking> tq = em.createQuery(cq);

		return tq.getSingleResult();
	}
}
