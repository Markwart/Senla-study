package by.senla.study.board.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.senla.study.board.api.service.IAdService;
import by.senla.study.board.model.dto.AdDto;
import by.senla.study.board.model.entity.Ad;
import by.senla.study.board.service.mapper.AdMapper;

@RestController
@RequestMapping(value = "/ad")
public class AdController extends AbstractController<Ad, Integer, AdDto> {

	private final IAdService adService;
	private final AdMapper mapper;

	@Autowired
	public AdController(IAdService adService, AdMapper mapper) {
		super(Ad.class, adService, mapper);
		this.adService = adService;
		this.mapper = mapper;
	}

	@GetMapping(value = "/{id}/history")
	public List<AdDto> sellerHistory(@PathVariable(name = "id", required = true) Integer sellerId) {
		List<Ad> entities = adService.sellerHistory(sellerId);
		List<AdDto> dtos = new ArrayList<>();
		for (Ad entity : entities) {
			dtos.add(mapper.toDto(entity));
		}
		return dtos;
	}
	
	@GetMapping(value = "/filter")
	public List<AdDto> filterByCategory(String category, String sortColumn, Boolean ascending) {
		List<Ad> entities = adService.findAdsByCategory(category, sortColumn, ascending);
		List<AdDto> dtos = new ArrayList<>();
		for (Ad entity : entities) {
			dtos.add(mapper.toDto(entity));
		}
		return dtos;
	}
	
	@GetMapping(value = "/search")
	public List<AdDto> search(String text) {
		List<Ad> entities = adService.searchByIndex(text);
		List<AdDto> dtos = new ArrayList<>();
		for (Ad entity : entities) {
			dtos.add(mapper.toDto(entity));
		}
		return dtos;
	}
	
	@PutMapping(value = "/{id}/up")
	public String moveAdOnTop(@PathVariable(name = "id", required = true) Integer id) {
		adService.moveAdOnTop(id);
		return UPDATE;
	}
}
