package by.senla.study.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.senla.study.api.dao.ICommentDao;
import by.senla.study.api.service.ICommentService;
import by.senla.study.model.entity.Comment;

@Service
@Transactional
public class CommentService extends AbstractService<Comment, Integer> implements ICommentService {
	
	private final Logger LOGGER = LogManager.getLogger(getEntityClass());

	private final ICommentDao commentDao;

	@Autowired
	public CommentService(ICommentDao commentDao) {
		super(Comment.class, commentDao);
		this.commentDao = commentDao;
	}
}
