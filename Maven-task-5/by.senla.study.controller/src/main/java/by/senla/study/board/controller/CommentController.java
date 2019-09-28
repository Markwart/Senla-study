package by.senla.study.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.senla.study.board.api.service.ICommentService;
import by.senla.study.board.model.dto.CommentDTO;
import by.senla.study.board.model.entity.Comment;
import by.senla.study.board.service.converterDTO.CommentFromDTO;
import by.senla.study.board.service.converterDTO.CommentToDTO;

@RestController
@RequestMapping(value = "/comment")
public class CommentController extends AbstractController<Comment, Integer, CommentDTO> {

	private final ICommentService commentService;
	private final CommentToDTO toDTOConverter;
	private final CommentFromDTO fromDTOConverter;

	@Autowired
	public CommentController(ICommentService commentService, CommentToDTO toDTOConverter,
			CommentFromDTO fromDTOConverter) {
		super(Comment.class, commentService, toDTOConverter, fromDTOConverter);
		this.commentService = commentService;
		this.toDTOConverter = toDTOConverter;
		this.fromDTOConverter = fromDTOConverter;
	}
}
