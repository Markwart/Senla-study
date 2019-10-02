package by.senla.study.board.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.senla.study.board.api.dao.IAdDao;
import by.senla.study.board.api.dao.ICommentDao;
import by.senla.study.board.api.dao.IUserAccountDao;
import by.senla.study.board.api.service.ICommentService;
import by.senla.study.board.model.entity.Comment;

@Service
@Transactional
public class CommentService extends AbstractService<Comment, Integer> implements ICommentService {

	private static final Logger LOGGER = LogManager.getLogger(CommentService.class);

	private final ICommentDao commentDao;
	private final IUserAccountDao userAccountDao;
	private final IAdDao adDao;
	

	@Autowired
	public CommentService(ICommentDao commentDao, IUserAccountDao userAccountDao, IAdDao adDao) {
		super(Comment.class, commentDao);
		this.commentDao = commentDao;
		this.userAccountDao = userAccountDao;
		this.adDao = adDao;
	}

	@Override
	public void addComment(Integer adId, Integer userId, Comment entity) {
		entity.setUserFrom(userAccountDao.getById(userId));
		entity.setAd(adDao.getById(adId));
		insert(entity);
	}
}
