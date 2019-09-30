package by.senla.study.board.api.DTOConverter;

import by.senla.study.board.model.dto.BaseDto;
import by.senla.study.board.model.entity.BaseEntity;

public interface IMapper<E extends BaseEntity, D extends BaseDto> {

	E toEntity(D dto);

	D toDto(E entity);
}