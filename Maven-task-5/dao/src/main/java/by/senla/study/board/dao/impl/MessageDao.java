package by.senla.study.board.dao.impl;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import by.senla.study.board.api.dao.IMessageDao;
import by.senla.study.board.model.entity.Message;
import by.senla.study.board.model.entity.Message_;

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
		from.fetch(Message_.user, JoinType.LEFT);
		from.fetch(Message_.chat, JoinType.LEFT);

		cq.where(cb.equal(from.get(Message_.id), id));
		TypedQuery<Message> tq = entityManager.createQuery(cq);

		return getSingleResult(tq);
	}
}
