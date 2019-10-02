package by.senla.study.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.senla.study.board.api.service.IRankingService;
import by.senla.study.board.model.dto.RankingDto;
import by.senla.study.board.model.entity.Ranking;
import by.senla.study.board.service.mapper.RankingMapper;

@RestController
@RequestMapping(value = "/ranking")
public class RankingController extends AbstractController<Ranking, Integer, RankingDto> {

	private final IRankingService rankingService;
	private final RankingMapper mapper;

	@Autowired
	public RankingController(IRankingService rankingService, RankingMapper mapper) {
		super(Ranking.class, rankingService, mapper);
		this.rankingService = rankingService;
		this.mapper = mapper;
	}
	
	@GetMapping(value = "/{id}/overallRanking")
	public Double overallRanking(@PathVariable(name = "id", required = true) Integer id) {
		return rankingService.getRankByUserID(id);
	}
}
