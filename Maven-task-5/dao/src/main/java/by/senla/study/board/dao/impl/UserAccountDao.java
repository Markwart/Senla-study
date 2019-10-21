package by.senla.study.board.dao.impl;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import by.senla.study.board.api.dao.IUserAccountDao;
import by.senla.study.board.model.entity.UserAccount;
import by.senla.study.board.model.entity.UserAccount_;

@Repository
public class UserAccountDao extends AbstractDao<UserAccount, Integer> implements IUserAccountDao {

	public UserAccountDao() {
		super(UserAccount.class);
	}

	@Override
	public UserAccount getFullInfo(Integer id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<UserAccount> cq = cb.createQuery(UserAccount.class);
		Root<UserAccount> from = cq.from(UserAccount.class);

		cq.select(from);
		from.fetch(UserAccount_.personalData, JoinType.LEFT);
		from.fetch(UserAccount_.ads, JoinType.LEFT);
		from.fetch(UserAccount_.comments, JoinType.LEFT);
		from.fetch(UserAccount_.messages, JoinType.LEFT);
		from.fetch(UserAccount_.rankingFrom, JoinType.LEFT);
		from.fetch(UserAccount_.rankingWhom, JoinType.LEFT);
		
		from.fetch(UserAccount_.wishlist, JoinType.LEFT);
		from.fetch(UserAccount_.chats, JoinType.LEFT);

		cq.where(cb.equal(from.get(UserAccount_.id), id));
		TypedQuery<UserAccount> tq = entityManager.createQuery(cq);

		return getSingleResult(tq);
	}
}
