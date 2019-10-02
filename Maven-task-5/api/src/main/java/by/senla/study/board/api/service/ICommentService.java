package by.senla.study.board.api.service;

import by.senla.study.board.model.entity.Comment;

public interface ICommentService extends GenericService<Comment, Integer> {

	void addComment(Integer adId, Integer userId, Comment entity);

}
