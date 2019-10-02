package by.senla.study.board.dao.impl;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.hibernate.query.criteria.internal.OrderImpl;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.stereotype.Repository;

import by.senla.study.board.api.dao.IAdDao;
import by.senla.study.board.model.entity.Ad;
import by.senla.study.board.model.entity.Ranking;
import by.senla.study.board.model.entity.UserAccount;
import by.senla.study.board.model.enums.Status;

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
		//cq.distinct(true);

		cq.where(cb.equal(from.get("id"), id));
		TypedQuery<Ad> tq = entityManager.createQuery(cq);

		return getSingleResult(tq);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Ad> searchByIndex(String text) {
		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search
				.getFullTextEntityManager(entityManager);

		QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Ad.class).get();
		org.apache.lucene.search.Query luceneQuery = qb.keyword().onFields("theme", "text").matching(text)
				.createQuery();

		FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Ad.class);

		org.apache.lucene.search.Sort sort = new Sort(new SortField("price", SortField.Type.STRING, true));
		jpaQuery.setSort(sort);

		return jpaQuery.getResultList();
	}

	@Override
	public List<Ad> sellerHistory(Integer sellerID) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Ad> cq = cb.createQuery(Ad.class);
		Root<Ad> from = cq.from(Ad.class);

		cq.select(from);
		Predicate sellerPred = cb.equal(from.get("seller"), sellerID);
		Predicate statusPred = cb.equal(from.get("status"), Status.CLOSED);
		cq.where(cb.and(sellerPred, statusPred));

		TypedQuery<Ad> tq = entityManager.createQuery(cq);
		return tq.getResultList();
	}

	@Override
	public List<Ad> findAdsByCategory(String category, String sortColumn, Boolean ascending) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Ad> cq = cb.createQuery(Ad.class);
		Root<Ad> from = cq.from(Ad.class);

		Join<Ad, UserAccount> joinUser = from.join("seller", JoinType.LEFT);
		Join<Ranking, UserAccount> joinRanking = joinUser.join("rankingWhom", JoinType.LEFT);

		cq.select(from);
		Predicate categoryPred = cb.equal(from.get("category").get("name"), category);
		Predicate statusPred = cb.notEqual(from.get("status"), Status.CLOSED);
		cq.where(cb.and(categoryPred, statusPred));

		if (sortColumn == null) {
			sortColumn = "id";
		}
		final Path<?> sortByColumn = from.get(sortColumn);
		final Expression<Double> sortByFeedback = cb.avg(joinRanking.get("feedback"));

		cq.groupBy(joinRanking.get("userWhom"));
		cq.orderBy(cb.desc(from.get("status")), new OrderImpl(sortByColumn, ascending),
				new OrderImpl(sortByFeedback, false));

		TypedQuery<Ad> tq = entityManager.createQuery(cq);
		return tq.getResultList();
	}
}
