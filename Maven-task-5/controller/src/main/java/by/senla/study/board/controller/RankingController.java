package by.senla.study.board.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.senla.study.board.api.service.IRankingService;
import by.senla.study.board.api.service.IUserAccountService;
import by.senla.study.board.dto.ResponseDto;
import by.senla.study.board.exception.RecordNotFoundException;
import by.senla.study.board.model.dto.RankingDto;
import by.senla.study.board.model.entity.Ranking;
import by.senla.study.board.service.mapper.RankingMapper;

@RestController
@RequestMapping(value = "/ranking")
public class RankingController extends AbstractController<Ranking, Integer, RankingDto> {

	private final IRankingService rankingService;
	private final RankingMapper mapper;
	private final IUserAccountService userAccountService;

	@Autowired
	public RankingController(IRankingService rankingService, RankingMapper mapper,
			IUserAccountService userAccountService) {
		super(Ranking.class, rankingService, mapper);
		this.rankingService = rankingService;
		this.mapper = mapper;
		this.userAccountService = userAccountService;
	}

	@GetMapping(value = "/{userId}/totalRanking")
	public ResponseDto overallRanking(@PathVariable(name = "userId", required = true) Integer userId) {
		return new ResponseDto(rankingService.getRankByUserID(userId).toString());
	}

	@PostMapping(value = "/{userWhomId}/add")
	public ResponseDto putFeedback(@PathVariable(name = "userWhomId", required = true) Integer userWhomId,
			@Valid @RequestBody RankingDto dto, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return new ResponseDto(INVALID);
		} else {
			dto.setUserWhomId(userWhomId);
			dto.setUserFromId(getLoggedUserId());
			Ranking entity = mapper.toEntity(dto);
			rankingService.insert(entity);
			return new ResponseDto(String.format(CREATED, getEntityClass().getSimpleName(), entity.getId()));
		}
	}

	@GetMapping(value = "/{userId}/feedback")
	public List<RankingDto> getFeedback(@PathVariable(name = "userId", required = true) Integer userId) {
		Set<Ranking> entities = userAccountService.getFullInfo(userId).getRankingWhom();
		List<RankingDto> dtos = new ArrayList<>();
		for (Ranking entity : entities) {
			dtos.add(mapper.toDto(entity));
		}
		if (dtos.isEmpty()) {
			throw new RecordNotFoundException();
		}
		return dtos;
	}
}
