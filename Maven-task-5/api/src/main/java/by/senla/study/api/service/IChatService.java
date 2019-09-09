package by.senla.study.api.service;

import java.util.List;

import by.senla.study.model.entity.Chat;

public interface IChatService {
	
	Chat createEntity();

	Chat get(Integer id);
	
	void update(Chat entity);

	void insert(Chat entity);

	void delete(Integer id);
	
	List<Chat> selectAll();

	Chat getFullInfo(Integer id);

}
