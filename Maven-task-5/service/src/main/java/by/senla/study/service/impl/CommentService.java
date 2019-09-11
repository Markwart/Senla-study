package by.senla.study.service.impl;

import java.util.List;

import by.senla.study.api.dao.ICommentDao;
import by.senla.study.api.service.ICommentService;
import by.senla.study.dao.impl.CommentDao;
import by.senla.study.model.entity.Comment;

public class CommentService extends AbstractService<Comment, Integer> implements ICommentService {

	private ICommentDao commentDao = CommentDao.getInstance();
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
	public Comment getByID(Integer id) {
		Comment entity = commentDao.getByID(id, entityManager);
		return entity;
	}

	@Override
	public List<Comment> selectAll() {
		List<Comment> commentList = commentDao.selectAll(entityManager);
		return commentList;
	}

	@Override
	public Comment getFullInfo(Integer id) {
		return commentDao.getFullInfo(id, entityManager);
	}

	@Override
	public void updateOperation(Comment entity) {
		commentDao.update(entity, entityManager);
	}

	@Override
	public void insertOperation(Comment entity) {
		commentDao.insert(entity, entityManager);
	}

	@Override
	public void deleteOperation(Integer id) {
		commentDao.deleteByID(id, entityManager);
	}

	@Override
	public Integer getPK(Comment entity) {
		return entity.getId();
	}

	@Override
	public void mergeOperation(Comment entity) {
		commentDao.merge(entity, entityManager);		
	}
}
