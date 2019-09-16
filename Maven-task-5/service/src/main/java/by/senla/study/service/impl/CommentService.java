package by.senla.study.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import by.senla.study.api.dao.ICommentDao;
import by.senla.study.api.service.ICommentService;
import by.senla.study.model.entity.Comment;

@Service
public class CommentService extends AbstractService<Comment, Integer> implements ICommentService {

	private final ICommentDao commentDao;

	@Autowired
	public CommentService(ICommentDao commentDao) {
		super(Comment.class, commentDao);
		this.commentDao = commentDao;
	}
}
