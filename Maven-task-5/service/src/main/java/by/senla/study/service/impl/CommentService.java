package by.senla.study.service.impl;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.senla.study.api.dao.ICommentDao;
import by.senla.study.api.service.ICommentService;
import by.senla.study.dao.impl.CommentDao;
import by.senla.study.model.entity.Comment;

public class CommentService extends BaseService implements ICommentService {

	private static final Logger LOGGER = LogManager.getLogger(CommentService.class);
	private ICommentDao dao = CommentDao.getInstance();
	private static CommentService instance;

	private CommentService() {
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

			LOGGER.log(Level.INFO, String.format("comment with id=%s was updated", entity.getId()));

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			LOGGER.log(Level.WARN, "EntityManager exception", e);
			throw new RuntimeException(e);
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void insert(Comment entity) {
		entityManager.getTransaction().begin();
		try {
			dao.insert(entity, entityManager);
			entityManager.getTransaction().commit();

			LOGGER.log(Level.INFO, String.format("new comment with id=%s was created", entity.getId()));

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			LOGGER.log(Level.WARN, "EntityManager exception", e);
			throw new RuntimeException(e);
		} finally {
			entityManager.close();
		}
	}

	@Override
	public void delete(Integer id) {
		entityManager.getTransaction().begin();
		try {
			dao.delete(id, entityManager);
			entityManager.getTransaction().commit();

			LOGGER.log(Level.INFO, String.format("user with id=%s was deleted", id));

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			LOGGER.log(Level.WARN, "EntityManager exception", e);
			throw new RuntimeException(e);
		} finally {
			entityManager.close();
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
