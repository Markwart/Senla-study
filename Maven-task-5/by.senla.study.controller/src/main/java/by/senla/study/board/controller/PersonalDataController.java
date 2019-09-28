package by.senla.study.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.senla.study.board.api.service.IPersonalDataService;
import by.senla.study.board.model.dto.PersonalDataDTO;
import by.senla.study.board.model.entity.PersonalData;
import by.senla.study.board.service.converterDTO.PersonalDataFromDTO;
import by.senla.study.board.service.converterDTO.PersonalDataToDTO;

@RestController
@RequestMapping(value = "/personalData")
public class PersonalDataController extends AbstractController<PersonalData, Integer, PersonalDataDTO> {

	private final IPersonalDataService personalDataService;
	private final PersonalDataToDTO toDTOConverter;
	private final PersonalDataFromDTO fromDTOConverter;

	@Autowired
	public PersonalDataController(IPersonalDataService personalDataService, PersonalDataToDTO toDTOConverter,
			PersonalDataFromDTO fromDTOConverter) {
		super(PersonalData.class, personalDataService, toDTOConverter, fromDTOConverter);
		this.personalDataService = personalDataService;
		this.toDTOConverter = toDTOConverter;
		this.fromDTOConverter = fromDTOConverter;
	}
}
