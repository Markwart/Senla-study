package by.senla.study.service.impl;

import org.springframework.stereotype.Service;

import by.senla.study.api.dao.ICommentDao;
import by.senla.study.api.service.ICommentService;
import by.senla.study.model.entity.Comment;

@Service
public class CommentService extends AbstractService<Comment, Integer> implements ICommentService {

	private static ICommentDao commentDao;

	private CommentService() {
		super(Comment.class, commentDao);
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
