package by.senla.study.board.service.mapper;

import java.util.Objects;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import by.senla.study.board.model.dto.PersonalDataDto;
import by.senla.study.board.model.entity.PersonalData;

@Component
public class PersonalDataMapper {

	@Autowired
	private ModelMapper mapper;

	public PersonalData toEntity(PersonalDataDto dto) {
		return Objects.isNull(dto) ? null : mapper.map(dto, PersonalData.class);
	}

	public PersonalDataDto toDto(PersonalData entity) {
		return Objects.isNull(entity) ? null : mapper.map(entity, PersonalDataDto.class);
	}
}
