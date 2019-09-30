package by.senla.study.board.service.mapper;

import javax.annotation.PostConstruct;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.senla.study.board.api.dao.ICategoryDao;
import by.senla.study.board.api.dao.IUserAccountDao;
import by.senla.study.board.model.dto.AdDto;
import by.senla.study.board.model.entity.Ad;

@Component
public class AdMapper extends AbstractMapper<Ad, AdDto> {

	private final ModelMapper mapper;
	private final IUserAccountDao userAccountDao;
	private final ICategoryDao categoryDao;

	@Autowired
	AdMapper(ModelMapper mapper, IUserAccountDao userAccountDao, ICategoryDao categoryDao) {
		super(Ad.class, AdDto.class);
		this.mapper = mapper;
		this.userAccountDao = userAccountDao;
		this.categoryDao = categoryDao;
	}

	@PostConstruct
	public void setupMapper() {
		mapper.createTypeMap(Ad.class, AdDto.class).addMappings(m -> m.skip(AdDto::setSellerId))
				.setPostConverter(toDtoConverter());
		mapper.getTypeMap(Ad.class, AdDto.class).addMappings(m -> m.skip(AdDto::setCategoryId))
				.setPostConverter(toDtoConverter());

		mapper.createTypeMap(AdDto.class, Ad.class).addMappings(m -> m.skip(Ad::setSeller))
				.setPostConverter(toEntityConverter());
		mapper.getTypeMap(AdDto.class, Ad.class).addMappings(m -> m.skip(Ad::setCategory))
				.setPostConverter(toEntityConverter());
	}

	@Override
	public void mapSpecificFields(Ad source, AdDto destination) {
		destination.setSellerId(source.getSeller().getId());
		destination.setCategoryId(source.getCategory().getId());
	}

	@Override
	public void mapSpecificFields(AdDto source, Ad destination) {
		destination.setSeller(userAccountDao.getByID(source.getSellerId()));
		destination.setCategory(categoryDao.getByID(source.getCategoryId()));
	}
}
