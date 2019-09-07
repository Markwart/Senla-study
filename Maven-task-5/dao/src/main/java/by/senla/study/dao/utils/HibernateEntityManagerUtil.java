package by.senla.study.dao.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HibernateEntityManagerUtil {

	private static final Logger LOGGER = LogManager.getLogger(HibernateEntityManagerUtil.class);
	private static final String PERSISTENT_UNIT_NAME = "sample";
	private static final EntityManagerFactory entityManagerFactory;

	static {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENT_UNIT_NAME);
		} catch (Exception e) {
			LOGGER.log(Level.WARN, "EntityManagerFactory exception", e);
			throw new RuntimeException(e);
		}
	}

	public static EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

}