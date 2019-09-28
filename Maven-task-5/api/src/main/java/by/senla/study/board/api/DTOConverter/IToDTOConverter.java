package by.senla.study.board.api.DTOConverter;

import java.util.function.Function;

public interface IToDTOConverter<T, DTO> extends Function<T, DTO> {

	DTO apply(T entity);
}
