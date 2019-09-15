package by.senla.study.service.impl;

import by.senla.study.api.dao.ICommentDao;
import by.senla.study.api.service.ICommentService;
import by.senla.study.dao.impl.CommentDao;
import by.senla.study.model.entity.Comment;

public class CommentService extends AbstractService<Comment, Integer> implements ICommentService {

	private static ICommentDao commentDao = CommentDao.getInstance();
	private static CommentService instance;

	private CommentService() {
		super(Comment.class, commentDao);
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
	public Integer getPK(Comment entity) {
		return entity.getId();
	}
}
