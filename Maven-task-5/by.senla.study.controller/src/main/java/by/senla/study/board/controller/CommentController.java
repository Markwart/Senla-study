package by.senla.study.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.senla.study.board.api.service.ICommentService;
import by.senla.study.board.model.dto.CommentDto;
import by.senla.study.board.model.entity.Comment;
import by.senla.study.board.service.mapper.CommentMapper;

@RestController
@RequestMapping(value = "/comment")
public class CommentController extends AbstractController<Comment, Integer, CommentDto> {

	private final ICommentService commentService;
	private final CommentMapper mapper;

	@Autowired
	public CommentController(ICommentService commentService, CommentMapper mapper) {
		super(Comment.class, commentService, mapper);
		this.commentService = commentService;
		this.mapper = mapper;
	}
}
