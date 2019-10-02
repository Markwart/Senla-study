package by.senla.study.board.service.mapper;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.senla.study.board.api.dao.IAdDao;
import by.senla.study.board.api.dao.IUserAccountDao;
import by.senla.study.board.model.dto.CommentDto;
import by.senla.study.board.model.entity.Comment;

@Component
public class CommentMapper extends AbstractMapper<Comment, CommentDto> {

	private final ModelMapper mapper;
	private final IAdDao adDao;
	private final IUserAccountDao userAccountDao;

	@Autowired
	CommentMapper(ModelMapper mapper, IAdDao adDao, IUserAccountDao userAccountDao) {
		super(Comment.class, CommentDto.class);
		this.mapper = mapper;
		this.adDao = adDao;
		this.userAccountDao = userAccountDao;
	}

	@PostConstruct
	public void setupMapper() {
		mapper.createTypeMap(Comment.class, CommentDto.class).addMappings(m -> m.skip(CommentDto::setAdId))
				.setPostConverter(toDtoConverter());
		mapper.getTypeMap(Comment.class, CommentDto.class).addMappings(m -> m.skip(CommentDto::setUserFromId))
				.setPostConverter(toDtoConverter());

		mapper.createTypeMap(CommentDto.class, Comment.class).addMappings(m -> m.skip(Comment::setAd))
				.setPostConverter(toEntityConverter());
		mapper.getTypeMap(CommentDto.class, Comment.class).addMappings(m -> m.skip(Comment::setUserFrom))
				.setPostConverter(toEntityConverter());
	}

	@Override
	public void mapSpecificFields(Comment source, CommentDto destination) {
		destination.setAdId(source.getAd().getId());
		destination.setUserFromId(source.getUserFrom().getId());
	}

	@Override
	public void mapSpecificFields(CommentDto source, Comment destination) {
		destination.setAd(adDao.getById(source.getAdId()));
		destination.setUserFrom(userAccountDao.getById(source.getUserFromId()));
	}
}
