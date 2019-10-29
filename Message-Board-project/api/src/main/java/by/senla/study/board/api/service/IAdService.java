package by.senla.study.board.api.service;

import java.util.List;

import by.senla.study.board.model.dto.AdDto;
import by.senla.study.board.model.entity.Ad;
import by.senla.study.board.model.search.AdSearchDto;

public interface IAdService extends GenericService<Ad, Integer, AdDto> {

	List<Ad> searchByIndex(String keyword);

	List<Ad> sellerHistory(Integer sellerID);

	void moveAdOnTop(Integer id);

	void closeAd(Integer id);

	List<Ad> filterAds(AdSearchDto dto);
}
