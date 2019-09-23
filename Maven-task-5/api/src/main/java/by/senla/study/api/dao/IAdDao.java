package by.senla.study.api.dao;


import java.util.List;

import by.senla.study.model.entity.Ad;

public interface IAdDao extends GenericDao<Ad, Integer> {

	List<Ad> searchByIndex(String text);

	List<Ad> sellerHistory(Integer sellerID);

	List<Ad> findAdsByCategory(String category, String column, Boolean ascending);
}
