package by.senla.study.dao.search;

import javax.persistence.EntityManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.search.jpa.FullTextEntityManager;

import by.senla.study.dao.utils.HibernateEntityManagerUtil;

public class HibernateSearchInitializer {

	private static final Logger LOGGER = LogManager.getLogger(HibernateSearchInitializer.class);
	private static EntityManager entityManager = HibernateEntityManagerUtil.getEntityManager();

	public static void initIndex() throws InterruptedException {
		LOGGER.info("index intialization started.");

		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search
				.getFullTextEntityManager(entityManager);
		fullTextEntityManager.createIndexer().startAndWait();

		LOGGER.info("index intialization completed.");
	}
}
