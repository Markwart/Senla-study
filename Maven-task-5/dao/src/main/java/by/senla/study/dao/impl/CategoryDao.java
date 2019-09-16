package by.senla.study.dao.impl;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import by.senla.study.api.dao.ICategoryDao;
import by.senla.study.model.entity.Category;

@Repository
public class CategoryDao extends AbstractDao<Category, Integer> implements ICategoryDao {

	private CategoryDao() {
		super(Category.class);
	}

	@Override
	public Category getFullInfo(Integer id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Category> cq = cb.createQuery(Category.class);
		Root<Category> from = cq.from(Category.class);

		cq.select(from);
		from.fetch("ads", JoinType.LEFT);

		cq.where(cb.equal(from.get("id"), id));
		TypedQuery<Category> tq = entityManager.createQuery(cq);

		return getSingleResult(tq);
	}
}
