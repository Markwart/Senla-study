package by.senla.study.dao.impl;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import by.senla.study.api.dao.IMessageDao;
import by.senla.study.model.entity.Message;

@Repository
public class MessageDao extends AbstractDao<Message, Integer> implements IMessageDao {

	public MessageDao() {
		super(Message.class);
	}

	@Override
	public Message getFullInfo(Integer id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Message> cq = cb.createQuery(Message.class);
		Root<Message> from = cq.from(Message.class);

		cq.select(from);
		from.fetch("user", JoinType.LEFT);
		from.fetch("chat", JoinType.LEFT);

		cq.where(cb.equal(from.get("id"), id));
		TypedQuery<Message> tq = entityManager.createQuery(cq);

		return getSingleResult(tq);
	}
}
