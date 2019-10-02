package by.senla.study.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

	@PostMapping(value = "/{adId}/add")
	public String addComment(@PathVariable(name = "adId", required = true) Integer adId, Integer userId, CommentDto dto) {
		Comment entity = mapper.toEntity(dto);
		commentService.addComment(adId, userId, entity);
		return CREATE;
	}
}
