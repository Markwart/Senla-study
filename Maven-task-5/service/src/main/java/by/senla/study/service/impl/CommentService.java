package by.senla.study.service.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.senla.study.api.dao.ICommentDao;
import by.senla.study.api.service.ICommentService;
import by.senla.study.dao.impl.CommentDao;
import by.senla.study.model.entity.Comment;

public class CommentService implements ICommentService {

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

	public Comment createEntity() {
		return new Comment();
	}

	public Comment get(Integer id) {
		Comment entity = dao.get(id);
		return entity;
	}

	public void update(Comment entity) {
		dao.update(entity);
	}

	public void insert(Comment entity) {
		dao.insert(entity);
	}

	public void delete(Integer id) {
		dao.delete(id);
	}

	public List<Comment> selectAll() {
		List<Comment> all = dao.selectAll();
		return all;
	}

}
