package by.senla.study.board.api.DTOConverter;

import java.util.function.Function;

public interface IFromDTOConverter<T, DTO> extends Function<DTO, T> {

	T apply(DTO entity);
}
