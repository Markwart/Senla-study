package by.senla.study.api.service;

import java.util.List;

import by.senla.study.model.entity.Message;

public interface IMessageService {

	Message createEntity();

	Message get(Integer id);
	
	void update(Message entity);

	void insert(Message entity);

	void delete(Integer id);
	
	List<Message> selectAll();
}
