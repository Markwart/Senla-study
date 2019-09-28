package by.senla.study.board.api.dao;


import java.util.List;

import by.senla.study.board.model.entity.Ad;

public interface IAdDao extends GenericDao<Ad, Integer> {

	List<Ad> searchByIndex(String text);

	List<Ad> sellerHistory(Integer sellerID);

	List<Ad> findAdsByCategory(String category, String sortColumn, Boolean ascending);
}
