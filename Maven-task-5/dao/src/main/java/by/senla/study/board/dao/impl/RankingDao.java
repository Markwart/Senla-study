package by.senla.study.board.dao.impl;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import by.senla.study.board.api.dao.IRankingDao;
import by.senla.study.board.model.entity.Ranking;

@Repository
public class RankingDao extends AbstractDao<Ranking, Integer> implements IRankingDao {

	public RankingDao() {
		super(Ranking.class);
	}

	@Override
	public Ranking getFullInfo(Integer id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Ranking> cq = cb.createQuery(Ranking.class);
		Root<Ranking> from = cq.from(Ranking.class);

		cq.select(from);
		from.fetch("userFrom", JoinType.LEFT);
		from.fetch("userWhom", JoinType.LEFT);

		cq.where(cb.equal(from.get("id"), id));
		TypedQuery<Ranking> tq = entityManager.createQuery(cq);

		return getSingleResult(tq);
	}

	@Override
	public Double getRankByUserID(Integer id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Double> cq = cb.createQuery(Double.class);
		Root<Ranking> from = cq.from(Ranking.class);

		cq.multiselect(cb.avg(from.get("feedback")));
		cq.where(cb.equal(from.get("userWhom"), id));

		TypedQuery<Double> tq = entityManager.createQuery(cq);
		return tq.getSingleResult();
	}
}
