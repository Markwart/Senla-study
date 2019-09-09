package by.senla.study.service.impl;

import javax.persistence.EntityManager;

import by.senla.study.dao.utils.HibernateEntityManagerUtil;

public class BaseService {

	protected EntityManager entityManager = HibernateEntityManagerUtil.getEntityManager();
}
