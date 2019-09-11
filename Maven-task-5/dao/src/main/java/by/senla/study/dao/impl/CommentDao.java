package by.senla.study.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import by.senla.study.api.dao.ICommentDao;
import by.senla.study.model.entity.Comment;

public class CommentDao extends AbstractDao<Comment, Integer> implements ICommentDao {

	private static CommentDao instance;

	private CommentDao() {
		super(Comment.class);
	}

	public static CommentDao getInstance() {
		if (instance == null) {
			instance = new CommentDao();
		}
		return instance;
	}
	
	@Override
	public Comment getFullInfo(Integer id, EntityManager em) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Comment> cq = cb.createQuery(Comment.class);
		Root<Comment> from = cq.from(Comment.class);

		cq.select(from);
		from.fetch("ad", JoinType.LEFT);
		from.fetch("userFrom", JoinType.LEFT);

		cq.where(cb.equal(from.get("id"), id));
		TypedQuery<Comment> tq = em.createQuery(cq);

		return getSingleResult(tq);
	}
}
