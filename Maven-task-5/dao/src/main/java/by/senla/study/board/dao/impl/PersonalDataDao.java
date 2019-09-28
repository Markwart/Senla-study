package by.senla.study.board.dao.impl;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import by.senla.study.board.api.dao.IPersonalDataDao;
import by.senla.study.board.model.entity.PersonalData;

@Repository
public class PersonalDataDao extends AbstractDao<PersonalData, Integer> implements IPersonalDataDao {

	public PersonalDataDao() {
		super(PersonalData.class);
	}

	@Override
	public PersonalData getFullInfo(Integer id) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<PersonalData> cq = cb.createQuery(PersonalData.class);
		Root<PersonalData> from = cq.from(PersonalData.class);

		cq.select(from);
		from.fetch("userAccount", JoinType.LEFT);

		cq.where(cb.equal(from.get("id"), id));
		TypedQuery<PersonalData> tq = entityManager.createQuery(cq);

		return getSingleResult(tq);
	}
}
