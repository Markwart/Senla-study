package by.senla.study.board.dao.impl;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import by.senla.study.board.api.dao.IRankingDao;
import by.senla.study.board.model.entity.Ranking;
import by.senla.study.board.model.entity.Ranking_;

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
		from.fetch(Ranking_.userFrom, JoinType.LEFT);
		from.fetch(Ranking_.userWhom, JoinType.LEFT);

		cq.where(cb.equal(from.get(Ranking_.id), id));
		TypedQuery<Ranking> tq = entityManager.createQuery(cq);

		return getSingleResult(tq);
	}

	@Override
	public Double getTotalRankByUserId(Integer userId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Double> cq = cb.createQuery(Double.class);
		Root<Ranking> from = cq.from(Ranking.class);

		cq.multiselect(cb.avg(from.get(Ranking_.feedback)));
		cq.where(cb.equal(from.get(Ranking_.userWhom), userId));

		TypedQuery<Double> tq = entityManager.createQuery(cq);
		return tq.getSingleResult();
	}
}
