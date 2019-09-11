package by.senla.study.api.service;

import by.senla.study.model.entity.Chat;

public interface IChatService extends GenericService<Chat, Integer>{
	
	Chat getFullInfo(Integer id);

}
