package by.senla.study.api.service;

import java.util.List;

import by.senla.study.model.entity.Comment;

public interface ICommentService {

	Comment createEntity();

	Comment get(Integer id);
	
	void update(Comment entity);

	void insert(Comment entity);

	void delete(Integer id);
	
	List<Comment> selectAll();
}
