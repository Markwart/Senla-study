package by.senla.study.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.senla.study.board.api.service.IPersonalDataService;
import by.senla.study.board.model.dto.PersonalDataDto;
import by.senla.study.board.model.entity.PersonalData;
import by.senla.study.board.service.mapper.PersonalDataMapper;

@RestController
@RequestMapping(value = "/personalData")
public class PersonalDataController extends AbstractController<PersonalData, Integer, PersonalDataDto> {

	private final IPersonalDataService personalDataService;
	private final PersonalDataMapper mapper;

	@Autowired
	public PersonalDataController(IPersonalDataService personalDataService, PersonalDataMapper mapper) {
		super(PersonalData.class, personalDataService,mapper);
		this.personalDataService = personalDataService;
		this.mapper = mapper;
	}
}
