package by.senla.study.board.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.senla.study.board.api.dao.ICommentDao;
import by.senla.study.board.api.service.ICommentService;
import by.senla.study.board.model.dto.CommentDto;
import by.senla.study.board.model.entity.Comment;

@Service
@Transactional
public class CommentService extends AbstractService<Comment, Integer, CommentDto> implements ICommentService {

	private static final Logger LOGGER = LogManager.getLogger(CommentService.class);

	private final ICommentDao commentDao;

	@Autowired
	public CommentService(ICommentDao commentDao) {
		super(Comment.class, commentDao);
		this.commentDao = commentDao;
	}	

	@Override
	public void setFieldsAndUpdate(Comment entity, CommentDto dto) {
		if (dto.getText() != null)
			entity.setText(dto.getText());
		update(entity);
	}
}
