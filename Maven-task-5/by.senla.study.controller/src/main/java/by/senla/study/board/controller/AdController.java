package by.senla.study.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.senla.study.board.api.service.IAdService;
import by.senla.study.board.model.dto.AdDTO;
import by.senla.study.board.model.entity.Ad;
import by.senla.study.board.service.converterDTO.AdFromDTO;
import by.senla.study.board.service.converterDTO.AdToDTO;

@RestController
@RequestMapping(value = "/ad")
public class AdController extends AbstractController<Ad, Integer, AdDTO> {

	private final IAdService adService;
	private final AdToDTO toDTOConverter;
	private final AdFromDTO fromDTOConverter;

	@Autowired
	public AdController(IAdService adService, AdToDTO toDTOConverter, AdFromDTO fromDTOConverter) {
		super(Ad.class, adService, toDTOConverter, fromDTOConverter);
		this.adService = adService;
		this.toDTOConverter = toDTOConverter;
		this.fromDTOConverter = fromDTOConverter;
	}
}
