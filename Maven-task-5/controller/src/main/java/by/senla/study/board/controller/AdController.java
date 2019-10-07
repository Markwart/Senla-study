package by.senla.study.board.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.senla.study.board.api.service.IAdService;
import by.senla.study.board.api.service.IUserAccountService;
import by.senla.study.board.dto.ResponseDto;
import by.senla.study.board.exception.RecordNotFoundException;
import by.senla.study.board.model.dto.AdDto;
import by.senla.study.board.model.entity.Ad;
import by.senla.study.board.model.enums.Status;
import by.senla.study.board.model.search.AdSearchDto;
import by.senla.study.board.service.mapper.AdMapper;

@RestController
@RequestMapping(value = "/ad")
public class AdController extends AbstractController<Ad, Integer, AdDto> {

	private static final String ADD_WISHLIST = "Ad with id=%d was added to wishlist of userAccount with id=%d";

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
	public ResponseDto createNewAd(@Valid @RequestBody AdDto dto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseDto(INVALID);
		} else {
			dto.setSellerId(getLoggedUserId());
			dto.setStatus(Status.OPEN);
			Ad entity = mapper.toEntity(dto);
			adService.insert(entity);
			return new ResponseDto(String.format(CREATED, getEntityClass().getSimpleName(), entity.getId()));
		}
	}

	@GetMapping(value = "/history")
	public List<AdDto> sellerHistory() {
		List<Ad> entities = adService.sellerHistory(getLoggedUserId());
		List<AdDto> dtos = new ArrayList<>();
		for (Ad entity : entities) {
			dtos.add(mapper.toDto(entity));
		}
		if (dtos.isEmpty()) {
			throw new RecordNotFoundException();
		}
		return dtos;
	}

	@GetMapping(value = "/filter")
	public List<AdDto> filterByCategory(@RequestBody AdSearchDto dto) {
		List<Ad> entities = adService.findAdsByCategory(dto);
		List<AdDto> dtos = new ArrayList<>();
		for (Ad entity : entities) {
			dtos.add(mapper.toDto(entity));
		}
		if (dtos.isEmpty()) {
			throw new RecordNotFoundException();
		}
		return dtos;
	}

	@GetMapping(value = "/search")
	public List<AdDto> search(@RequestBody String keyword) {
		List<Ad> entities = adService.searchByIndex(keyword);
		List<AdDto> dtos = new ArrayList<>();
		for (Ad entity : entities) {
			dtos.add(mapper.toDto(entity));
		}
		if (dtos.isEmpty()) {
			throw new RecordNotFoundException();
		}
		return dtos;
	}

	@PutMapping(value = "/{adId}/up")
	public ResponseDto moveAdOnTop(@PathVariable(name = "adId", required = true) Integer adId) {
		adService.moveAdOnTop(adId);
		return new ResponseDto(String.format(UPDATED, getEntityClass().getSimpleName(), adId));
	}

	@PutMapping(value = "/{adId}/close")
	public ResponseDto closeAd(@PathVariable(name = "adId", required = true) Integer adId) {
		adService.closeAd(adId);
		return new ResponseDto(String.format(UPDATED, getEntityClass().getSimpleName(), adId));
	}

	@GetMapping(value = { "/{sellerId}/sellerAds", "/myAds" })
	public List<AdDto> myAds(HttpServletRequest request,
			@PathVariable(name = "sellerId", required = false) Integer sellerId) {

		Integer id;

		if (request.getRequestURI().contains("/myAds")) {
			id = getLoggedUserId();
		} else {
			id = sellerId;
		}

		Set<Ad> entities = userAccountService.getFullInfo(id).getAds();
		List<AdDto> dtos = new ArrayList<>();
		for (Ad entity : entities) {
			dtos.add(mapper.toDto(entity));
		}
		if (dtos.isEmpty()) {
			throw new RecordNotFoundException();
		}
		return dtos;
	}

	@GetMapping(value = "/wishlist")
	public List<AdDto> wishlist() {
		Set<Ad> entities = userAccountService.getFullInfo(getLoggedUserId()).getWishlist();
		List<AdDto> dtos = new ArrayList<>();
		for (Ad entity : entities) {
			dtos.add(mapper.toDto(entity));
		}
		if (dtos.isEmpty()) {
			throw new RecordNotFoundException();
		}
		return dtos;
	}

	@PutMapping(value = "/{adId}/wishlistAdd")
	public ResponseDto addToWishList(@PathVariable(name = "adId", required = true) Integer adId) {
		userAccountService.addToWishList(adId, getLoggedUserId());
		return new ResponseDto(String.format(ADD_WISHLIST, adId, getLoggedUserId()));
	}
}
