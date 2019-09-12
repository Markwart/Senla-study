package by.senla.study.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

import by.senla.study.api.dao.IPersonalDataDao;
import by.senla.study.model.entity.PersonalData;

public class PersonalDataDao extends AbstractDao<PersonalData, Integer> implements IPersonalDataDao {

	private static PersonalDataDao instance;

	private PersonalDataDao() {
		super(PersonalData.class);
	}

	public static PersonalDataDao getInstance() {
		if (instance == null) {
			instance = new PersonalDataDao();
		}
		return instance;
	}

	@Override
	public PersonalData getFullInfo(Integer id, EntityManager em) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PersonalData> cq = cb.createQuery(PersonalData.class);
		Root<PersonalData> from = cq.from(PersonalData.class);

		cq.select(from);
		from.fetch("userAccount", JoinType.LEFT);

		cq.where(cb.equal(from.get("id"), id));
		TypedQuery<PersonalData> tq = em.createQuery(cq);

		return getSingleResult(tq);
	}
}
