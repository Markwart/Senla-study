package by.senla.study.board.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import by.senla.study.board.api.dao.IChatDao;
import by.senla.study.board.api.dao.IUserAccountDao;
import by.senla.study.board.api.service.IChatService;
import by.senla.study.board.model.dto.ChatDto;
import by.senla.study.board.model.entity.Chat;
import by.senla.study.board.model.entity.UserAccount;

@Service
@Transactional
public class ChatService extends AbstractService<Chat, Integer, ChatDto> implements IChatService {

	private static final Logger LOGGER = LogManager.getLogger(ChatService.class);

	private final IChatDao chatDao;
	private final IUserAccountDao userAccountDao;

	@Autowired
	public ChatService(IChatDao chatDao, IUserAccountDao userAccountDao) {
		super(Chat.class, chatDao);
		this.chatDao = chatDao;
		this.userAccountDao = userAccountDao;
	}

	@Override
	public void setFieldsAndUpdate(Chat entity, ChatDto dto) {
		if (dto.getName() != null)
			entity.setName(dto.getName());
		update(entity);
	}

	@Override
	public void createNewChat(Integer sellerId, Integer userId) {
		Chat entity = new Chat();
		UserAccount user = userAccountDao.getFullInfo(userId);
		UserAccount seller = userAccountDao.getFullInfo(sellerId);

		entity.setName(new StringBuilder().append(user.getName()).append("-").append(seller.getName()).toString());

		insert(entity);
		user.getChats().add(entity);
		seller.getChats().add(entity);

		userAccountDao.update(user);
		userAccountDao.update(seller);
	}
}
