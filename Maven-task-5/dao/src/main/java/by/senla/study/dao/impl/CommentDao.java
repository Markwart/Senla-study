package by.senla.study.dao.impl;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import by.senla.study.api.dao.ICommentDao;
import by.senla.study.model.entity.Comment;

@Repository
public class CommentDao extends AbstractDao<Comment, Integer> implements ICommentDao {

	public CommentDao() {
		super(Comment.class);
	}

	@Override
	public Comment getFullInfo(Integer id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Comment> cq = cb.createQuery(Comment.class);
		Root<Comment> from = cq.from(Comment.class);

		cq.select(from);
		from.fetch("ad", JoinType.LEFT);
		from.fetch("userFrom", JoinType.LEFT);

		cq.where(cb.equal(from.get("id"), id));
		TypedQuery<Comment> tq = entityManager.createQuery(cq);

		return getSingleResult(tq);
	}
}
