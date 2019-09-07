package by.senla.study.dao.impl;

import by.senla.study.api.dao.ICommentDao;
import by.senla.study.model.entity.Comment;

public class CommentDao extends AbstractDao<Comment, Integer> implements ICommentDao {

	private static CommentDao instance;

	private CommentDao() {
	}

	public static CommentDao getInstance() {
		if (instance == null) {
			instance = new CommentDao();
		}
		return instance;
	}
}
