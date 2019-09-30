package by.senla.study.board.service.mapper;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.senla.study.board.api.dao.IChatDao;
import by.senla.study.board.api.dao.IUserAccountDao;
import by.senla.study.board.model.dto.MessageDto;
import by.senla.study.board.model.entity.Message;

@Component
public class MessageMapper extends AbstractMapper<Message, MessageDto> {

	private final ModelMapper mapper;
	private final IUserAccountDao userAccountDao;
	private final IChatDao chatDao;

	@Autowired
	MessageMapper(ModelMapper mapper, IUserAccountDao userAccountDao, IChatDao chatDao) {
		super(Message.class, MessageDto.class);
		this.mapper = mapper;
		this.userAccountDao = userAccountDao;
		this.chatDao = chatDao;
	}

	@PostConstruct
	public void setupMapper() {
		mapper.createTypeMap(Message.class, MessageDto.class).addMappings(m -> m.skip(MessageDto::setChatId))
				.setPostConverter(toDtoConverter());
		mapper.getTypeMap(Message.class, MessageDto.class).addMappings(m -> m.skip(MessageDto::setUserId))
				.setPostConverter(toDtoConverter());

		mapper.createTypeMap(MessageDto.class, Message.class).addMappings(m -> m.skip(Message::setChat))
				.setPostConverter(toEntityConverter());
		mapper.getTypeMap(MessageDto.class, Message.class).addMappings(m -> m.skip(Message::setUser))
				.setPostConverter(toEntityConverter());
	}

	@Override
	public void mapSpecificFields(Message source, MessageDto destination) {
		destination.setChatId(source.getChat().getId());
		destination.setUserId(source.getUser().getId());
	}

	@Override
	public void mapSpecificFields(MessageDto source, Message destination) {
		destination.setChat(chatDao.getByID(source.getChatId()));
		destination.setUser(userAccountDao.getByID(source.getUserId()));
	}
}
