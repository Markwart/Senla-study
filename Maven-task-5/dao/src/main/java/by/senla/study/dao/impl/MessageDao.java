package by.senla.study.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import by.senla.study.api.dao.IMessageDao;
import by.senla.study.model.entity.Message;

public class MessageDao extends AbstractDao<Message, Integer> implements IMessageDao {

	private static MessageDao instance;

	private MessageDao() {
		super(Message.class);
	}

	public static MessageDao getInstance() {
		if (instance == null) {
			instance = new MessageDao();
		}
		return instance;
	}
	
	@Override
	public Message getFullInfo(Integer id, EntityManager entityManager) {
		EntityManager em = entityManager;
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Message> cq = cb.createQuery(Message.class);
		Root<Message> from = cq.from(Message.class);

		cq.select(from);
		from.fetch("user", JoinType.LEFT);
		from.fetch("chat", JoinType.LEFT);

		cq.where(cb.equal(from.get("id"), id));
		TypedQuery<Message> tq = em.createQuery(cq);

		return tq.getSingleResult();
	}
}
