package by.senla.study.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import by.senla.study.api.dao.ICategoryDao;
import by.senla.study.model.entity.Category;

public class CategoryDao extends AbstractDao<Category, Integer> implements ICategoryDao {

	private static CategoryDao instance;

	private CategoryDao() {
		super(Category.class);
	}

	public static CategoryDao getInstance() {
		if (instance == null) {
			instance = new CategoryDao();
		}
		return instance;
	}

	@Override
	public Category getFullInfo(Integer id, EntityManager em) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Category> cq = cb.createQuery(Category.class);
		Root<Category> from = cq.from(Category.class);

		cq.select(from);
		from.fetch("ads", JoinType.LEFT);

		cq.where(cb.equal(from.get("id"), id));
		TypedQuery<Category> tq = em.createQuery(cq);

		return getSingleResult(tq);
	}
}
