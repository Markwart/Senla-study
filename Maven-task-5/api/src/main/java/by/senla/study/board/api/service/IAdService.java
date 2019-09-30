package by.senla.study.board.api.service;

import java.util.List;

import by.senla.study.board.model.entity.Ad;

public interface IAdService extends GenericService<Ad, Integer> {

	List<Ad> searchByIndex(String text);

	List<Ad> sellerHistory(Integer sellerID);

	List<Ad> findAdsByCategory(String category, String sortColumn, Boolean ascending);

	void moveAdOnTop(Integer id);
}
