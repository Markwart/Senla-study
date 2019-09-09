package by.senla.study.service.impl;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.senla.study.api.dao.ICommentDao;
import by.senla.study.api.service.ICommentService;
import by.senla.study.dao.impl.CommentDao;
import by.senla.study.model.entity.Comment;

public class CommentService extends BaseService<Comment> implements ICommentService {

	private static final Logger LOGGER = LogManager.getLogger(CommentService.class);
	private ICommentDao dao = CommentDao.getInstance();
	private static CommentService instance;

	private CommentService() {
		super(Comment.class);
	}

	public static CommentService getInstance() {
		if (instance == null) {
			instance = new CommentService();
		}
		return instance;
	}

	@Override
	public Comment createEntity() {
		return new Comment();
	}

	@Override
	public Comment get(Integer id) {
		Comment entity = dao.get(id, entityManager);
		return entity;
	}

	@Override
	public void update(Comment entity) {
		entityManager.getTransaction().begin();
		try {
			dao.update(entity, entityManager);
			entityManager.getTransaction().commit();

			LOGGER.log(Level.INFO, String.format(UPDATED, getEntityClassName(), entity.getId()));

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			LOGGER.log(Level.WARN, EXCEPTION, e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void insert(Comment entity) {
		entityManager.getTransaction().begin();
		try {
			dao.insert(entity, entityManager);
			entityManager.getTransaction().commit();

			LOGGER.log(Level.INFO, String.format(CREATED, getEntityClassName(), entity.getId()));

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			LOGGER.log(Level.WARN, EXCEPTION, e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(Integer id) {
		entityManager.getTransaction().begin();
		try {
			dao.delete(id, entityManager);
			entityManager.getTransaction().commit();

			LOGGER.log(Level.INFO, String.format(DELETED, getEntityClassName(), id));

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			LOGGER.log(Level.WARN, EXCEPTION, e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Comment> selectAll() {
		List<Comment> all = dao.selectAll(entityManager);
		return all;
	}

	@Override
	public Comment getFullInfo(Integer id) {
		return dao.getFullInfo(id, entityManager);
	}
}
