package by.senla.study.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import by.senla.study.api.dao.IUserAccountDao;
import by.senla.study.model.entity.UserAccount;

@Repository
public class UserAccountDao extends AbstractDao<UserAccount, Integer> implements IUserAccountDao {

	private UserAccountDao() {
		super(UserAccount.class);
	}

	private static UserAccountDao instance;

	public static UserAccountDao getInstance() {
		if (instance == null) {
			instance = new UserAccountDao();
		}
		return instance;
	}
	
	@Override
	public UserAccount getFullInfo(Integer id, EntityManager em) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<UserAccount> cq = cb.createQuery(UserAccount.class);
		Root<UserAccount> from = cq.from(UserAccount.class);

		cq.select(from);
		from.fetch("personalData", JoinType.LEFT);
		from.fetch("ads", JoinType.LEFT);
		from.fetch("comments", JoinType.LEFT);
		from.fetch("messages", JoinType.LEFT);
		from.fetch("rankingFrom", JoinType.LEFT);
		from.fetch("rankingWhom", JoinType.LEFT);
		from.fetch("comments", JoinType.LEFT);
		from.fetch("wishlist", JoinType.LEFT);
		from.fetch("chats", JoinType.LEFT);

		cq.where(cb.equal(from.get("id"), id));
		TypedQuery<UserAccount> tq = em.createQuery(cq);

		return getSingleResult(tq);
	}
}
