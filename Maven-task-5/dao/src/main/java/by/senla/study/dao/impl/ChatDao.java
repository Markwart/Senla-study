package by.senla.study.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import by.senla.study.api.dao.IChatDao;
import by.senla.study.model.entity.Chat;

@Repository
public class ChatDao extends AbstractDao<Chat, Integer> implements IChatDao {
	
	private static ChatDao instance;

	private ChatDao() {
		super(Chat.class);
	}

	public static ChatDao getInstance() {
		if (instance == null) {
			instance = new ChatDao();
		}
		return instance;
	}
	
	@Override
	public Chat getFullInfo(Integer id, EntityManager em) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Chat> cq = cb.createQuery(Chat.class);
		Root<Chat> from = cq.from(Chat.class);

		cq.select(from);
		from.fetch("messages", JoinType.LEFT);

		cq.where(cb.equal(from.get("id"), id));
		TypedQuery<Chat> tq = em.createQuery(cq);

		return getSingleResult(tq);
	}
}
