package by.senla.study.board.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.senla.study.board.api.service.IAdService;
import by.senla.study.board.api.service.ICommentService;
import by.senla.study.board.dto.ResponseDto;
import by.senla.study.board.exception.RecordNotFoundException;
import by.senla.study.board.model.dto.CommentDto;
import by.senla.study.board.model.entity.Comment;
import by.senla.study.board.service.mapper.CommentMapper;

@RestController
@RequestMapping(value = "/comment")
public class CommentController extends AbstractController<Comment, Integer, CommentDto> {

	private final ICommentService commentService;
	private final CommentMapper mapper;
	private final IAdService adService;

	@Autowired
	public CommentController(ICommentService commentService, CommentMapper mapper, IAdService adService) {
		super(Comment.class, commentService, mapper);
		this.commentService = commentService;
		this.mapper = mapper;
		this.adService = adService;
	}

	@PostMapping(value = "/{adId}/add")
	public ResponseDto addComment(@PathVariable(name = "adId", required = true) Integer adId,
			@Valid @RequestBody CommentDto dto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseDto(INVALID);
		} else {
			dto.setAdId(adId);
			dto.setUserFromId(getLoggedUserId());
			Comment entity = mapper.toEntity(dto);
			commentService.insert(entity);
			return new ResponseDto(String.format(CREATED, getEntityClass().getSimpleName(), entity.getId()));
		}
	}

	@GetMapping(value = "/{adId}/comments")
	public List<CommentDto> getCommentsOfAd(@PathVariable(name = "adId", required = true) Integer adId) {
		Set<Comment> entities = adService.getFullInfo(adId).getComments();
		List<CommentDto> dtos = new ArrayList<>();
		for (Comment entity : entities) {
			dtos.add(mapper.toDto(entity));
		}
		if (dtos.isEmpty()) {
			throw new RecordNotFoundException();
		}
		return dtos;
	}
}
