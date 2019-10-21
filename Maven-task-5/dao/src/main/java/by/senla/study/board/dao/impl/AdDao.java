package by.senla.study.board.dao.impl;

import java.util.ArrayList;
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
import by.senla.study.board.model.entity.Ad_;
import by.senla.study.board.model.entity.Category_;
import by.senla.study.board.model.entity.Ranking;
import by.senla.study.board.model.entity.Ranking_;
import by.senla.study.board.model.entity.UserAccount;
import by.senla.study.board.model.entity.UserAccount_;
import by.senla.study.board.model.enums.Status;
import by.senla.study.board.model.search.AdSearchDto;

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
		from.fetch(Ad_.seller, JoinType.LEFT);
		from.fetch(Ad_.category, JoinType.LEFT);
		from.fetch(Ad_.comments, JoinType.LEFT);

		cq.where(cb.equal(from.get(Ad_.id), id));
		TypedQuery<Ad> tq = entityManager.createQuery(cq);

		return getSingleResult(tq);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Ad> searchByIndex(String keyword) {
		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search
				.getFullTextEntityManager(entityManager);

		QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Ad.class).get();
		org.apache.lucene.search.Query luceneQuery = qb.keyword().onFields(Ad_.THEME, Ad_.TEXT).matching(keyword)
				.createQuery();

		FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Ad.class);

		org.apache.lucene.search.Sort sort = new Sort(new SortField(Ad_.STATUS, SortField.Type.STRING, false));
		jpaQuery.setSort(sort);

		return jpaQuery.getResultList();
	}

	@Override
	public List<Ad> sellerHistory(Integer sellerId) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Ad> cq = cb.createQuery(Ad.class);
		Root<Ad> from = cq.from(Ad.class);

		cq.select(from);
		Predicate sellerPred = cb.equal(from.get(Ad_.seller), sellerId);
		Predicate statusPred = cb.equal(from.get(Ad_.status), Status.CLOSED);
		cq.where(cb.and(sellerPred, statusPred));

		TypedQuery<Ad> tq = entityManager.createQuery(cq);
		return tq.getResultList();
	}

	@Override
	public List<Ad> filterAds(AdSearchDto dto) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Ad> cq = cb.createQuery(Ad.class);
		Root<Ad> from = cq.from(Ad.class);

		Join<Ad, UserAccount> joinUser = from.join(Ad_.seller, JoinType.LEFT);
		Join<UserAccount, Ranking> joinRanking = joinUser.join(UserAccount_.rankingWhom, JoinType.LEFT);

		cq.select(from);

		List<Predicate> predicates = new ArrayList<>();
		Predicate statusPred = cb.notEqual(from.get(Ad_.status), Status.CLOSED);
		predicates.add(statusPred);

		if (dto.getCategory() != null) {
			Predicate categoryPred = cb.equal(from.get(Ad_.category).get(Category_.name), dto.getCategory());
			predicates.add(categoryPred);
		}
		if (dto.getKeyword() != null) {
			Predicate keywordPred = cb.or(cb.like(from.get(Ad_.text), "%" + dto.getKeyword() + "%"),
					cb.like(from.get(Ad_.theme), "%" + dto.getKeyword() + "%"));
			predicates.add(keywordPred);
		}
		if (dto.getPriceStart() != null & dto.getPriceFinal() != null) {
			Predicate pricePred = cb.between(from.get(Ad_.price), dto.getPriceStart(), dto.getPriceFinal());
			predicates.add(pricePred);
		}
		cq.where(cb.and(predicates.toArray(new Predicate[0])));

		if (dto.getSortColumn() == null) {
			dto.setSortColumn(Ad_.ID);
		}
		if (dto.getAscending() == null) {
			dto.setAscending(true);
		}
		final Path<?> sortByColumn = from.get(dto.getSortColumn());
		final Expression<Double> sortByFeedback = cb.avg(joinRanking.get(Ranking_.feedback));

		cq.groupBy(from.get(Ad_.id));
		cq.orderBy(cb.desc(from.get(Ad_.status)), new OrderImpl(sortByColumn, dto.getAscending()),
				new OrderImpl(sortByFeedback, false));

		TypedQuery<Ad> tq = entityManager.createQuery(cq);
		return tq.getResultList();
	}
}
