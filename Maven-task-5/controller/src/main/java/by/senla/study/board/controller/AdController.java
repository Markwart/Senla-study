package by.senla.study.board.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.senla.study.board.api.service.IAdService;
import by.senla.study.board.api.service.IUserAccountService;
import by.senla.study.board.model.dto.AdDto;
import by.senla.study.board.model.entity.Ad;
import by.senla.study.board.model.enums.Status;
import by.senla.study.board.service.mapper.AdMapper;

@RestController
@RequestMapping(value = "/ad")
public class AdController extends AbstractController<Ad, Integer, AdDto> {

	private final IAdService adService;
	private final AdMapper mapper;
	private final IUserAccountService userAccountService;

	@Autowired
	public AdController(IAdService adService, AdMapper mapper, IUserAccountService userAccountService) {
		super(Ad.class, adService, mapper);
		this.adService = adService;
		this.mapper = mapper;
		this.userAccountService = userAccountService;
	}

	@PostMapping(value = "/createNew")
	public String createNewAd(Integer sellerId, AdDto dto) {
		dto.setSellerId(sellerId);
		dto.setStatus(Status.OPEN);
		Ad entity = mapper.toEntity(dto);
		adService.insert(entity);
		return String.format(CREATED, getEntityClass().getSimpleName(), entity.getId());
	}

	@GetMapping(value = "/{userId}/history")
	public List<AdDto> sellerHistory(@PathVariable(name = "userId", required = true) Integer sellerId) {
		List<Ad> entities = adService.sellerHistory(sellerId);
		List<AdDto> dtos = new ArrayList<>();
		for (Ad entity : entities) {
			dtos.add(mapper.toDto(entity));
		}
		return dtos;
	}

	@GetMapping(value = "/filter")
	public List<AdDto> filterByCategory(String category, String sortColumn) {
		List<Ad> entities = adService.findAdsByCategory(category, sortColumn);
		List<AdDto> dtos = new ArrayList<>();
		for (Ad entity : entities) {
			dtos.add(mapper.toDto(entity));
		}
		return dtos;
	}

	@GetMapping(value = "/search")
	public List<AdDto> search(String keyword) {
		List<Ad> entities = adService.searchByIndex(keyword);
		List<AdDto> dtos = new ArrayList<>();
		for (Ad entity : entities) {
			dtos.add(mapper.toDto(entity));
		}
		return dtos;
	}

	@PutMapping(value = "/{adId}/up")
	public String moveAdOnTop(@PathVariable(name = "adId", required = true) Integer adId) {
		adService.moveAdOnTop(adId);
		return String.format(UPDATED, getEntityClass().getSimpleName(), adId);
	}

	@PutMapping(value = "/{adId}/close")
	public String closeAd(@PathVariable(name = "adId", required = true) Integer adId) {
		adService.closeAd(adId);
		return String.format(UPDATED, getEntityClass().getSimpleName(), adId);
	}

	@GetMapping(value = "/myAds")
	public List<AdDto> myAds(Integer userId) {
		Set<Ad> entities = userAccountService.getFullInfo(userId).getAds();
		List<AdDto> dtos = new ArrayList<>();
		for (Ad entity : entities) {
			dtos.add(mapper.toDto(entity));
		}
		return dtos;
	}

	@GetMapping(value = "/wishlist")
	public List<AdDto> wishlist(Integer userId) {
		Set<Ad> entities = userAccountService.getFullInfo(userId).getWishlist();
		List<AdDto> dtos = new ArrayList<>();
		for (Ad entity : entities) {
			dtos.add(mapper.toDto(entity));
		}
		return dtos;
	}

	@PutMapping(value = "/{adId}/wishlistAdd")
	public String addToWishList(@PathVariable(name = "adId", required = true) Integer adId, Integer userId) {
		userAccountService.addToWishList(adId, userId);
		return String.format(UPDATED, getEntityClass().getSimpleName(), userId);
	}
}
