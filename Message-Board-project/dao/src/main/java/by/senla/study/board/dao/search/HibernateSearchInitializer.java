package by.senla.study.board.dao.search;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.stereotype.Component;

@Component
public class HibernateSearchInitializer {

	private static final Logger LOGGER = LogManager.getLogger(HibernateSearchInitializer.class);

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	@PostConstruct
	private void initIndex() throws InterruptedException {
		LOGGER.info("index intialization started.");

		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		fullTextEntityManager.createIndexer().startAndWait();

		LOGGER.info("index intialization completed.");
	}
}
