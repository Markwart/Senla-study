package by.senla.study.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import by.senla.study.board.api.service.IRankingService;
import by.senla.study.board.model.dto.RankingDTO;
import by.senla.study.board.model.entity.Ranking;
import by.senla.study.board.service.converterDTO.RankingFromDTO;
import by.senla.study.board.service.converterDTO.RankingToDTO;

@RestController
@RequestMapping(value = "/ranking")
public class RankingController extends AbstractController<Ranking, Integer, RankingDTO> {

	private final IRankingService rankingService;
	private final RankingToDTO toDTOConverter;
	private final RankingFromDTO fromDTOConverter;

	@Autowired
	public RankingController(IRankingService rankingService, RankingToDTO toDTOConverter,
			RankingFromDTO fromDTOConverter) {
		super(Ranking.class, rankingService, toDTOConverter, fromDTOConverter);
		this.rankingService = rankingService;
		this.toDTOConverter = toDTOConverter;
		this.fromDTOConverter = fromDTOConverter;
	}
}
