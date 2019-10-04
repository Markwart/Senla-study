package by.senla.study.board.api.service;

import java.util.List;

import by.senla.study.board.model.dto.AdDto;
import by.senla.study.board.model.entity.Ad;

public interface IAdService extends GenericService<Ad, Integer, AdDto> {

	List<Ad> searchByIndex(String keyword);

	List<Ad> sellerHistory(Integer sellerID);

	List<Ad> findAdsByCategory(String category, String sortColumn);

	void moveAdOnTop(Integer id);

	void closeAd(Integer id);
}
