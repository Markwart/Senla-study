package by.senla.study.api.dao;

import javax.persistence.EntityManager;

import by.senla.study.model.entity.Comment;

public interface ICommentDao extends IDao<Comment, Integer> {

	Comment getFullInfo(Integer id, EntityManager entityManager);

}
