package by.senla.study.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import by.senla.study.api.dao.IAdDao;
import by.senla.study.model.entity.Ad;

@Repository
public class AdDao extends AbstractDao<Ad, Integer> implements IAdDao {

	public AdDao() {
		super(Ad.class);
	}

	@Override
	public Ad getFullInfo(Integer id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Ad> cq = cb.createQuery(Ad.class);
		Root<Ad> from = cq.from(Ad.class);

		cq.select(from);
		from.fetch("seller", JoinType.LEFT);
		from.fetch("category", JoinType.LEFT);
		from.fetch("comments", JoinType.LEFT);

		cq.where(cb.equal(from.get("id"), id));
		TypedQuery<Ad> tq = entityManager.createQuery(cq);

		return getSingleResult(tq);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Ad> searchByIndex(String text) {
		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(entityManager);

		QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Ad.class).get();
		org.apache.lucene.search.Query luceneQuery = qb.keyword().onFields("theme", "text").matching(text).createQuery();

		javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Ad.class);

		return jpaQuery.getResultList();
	}
}
