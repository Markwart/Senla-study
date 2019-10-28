package by.senla.study.board.service.mapper;

import java.util.Objects;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import by.senla.study.board.api.сonverter.IMapper;
import by.senla.study.board.model.dto.BaseDto;
import by.senla.study.board.model.entity.BaseEntity;

public abstract class AbstractMapper<E extends BaseEntity, D extends BaseDto> implements IMapper<E, D> {

	@Autowired
	private ModelMapper mapper;

	private Class<E> entityClass;
	private Class<D> dtoClass;

	AbstractMapper(Class<E> entityClass, Class<D> dtoClass) {
		this.entityClass = entityClass;
		this.dtoClass = dtoClass;
	}

	@Override
	public E toEntity(D dto) {
		return Objects.isNull(dto) ? null : mapper.map(dto, entityClass);
	}

	@Override
	public D toDto(E entity) {
		return Objects.isNull(entity) ? null : mapper.map(entity, dtoClass);
	}

	Converter<E, D> toDtoConverter() {
		return context -> {
			E source = context.getSource();
			D destination = context.getDestination();
			mapSpecificFields(source, destination);
			return context.getDestination();
		};
	}

	Converter<D, E> toEntityConverter() {
		return context -> {
			D source = context.getSource();
			E destination = context.getDestination();
			mapSpecificFields(source, destination);
			return context.getDestination();
		};
	}

	void mapSpecificFields(E source, D destination) {
	}

	void mapSpecificFields(D source, E destination) {
	}
}