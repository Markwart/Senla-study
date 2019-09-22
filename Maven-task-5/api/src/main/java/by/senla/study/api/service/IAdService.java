package by.senla.study.api.service;

import java.util.List;

import by.senla.study.model.entity.Ad;

public interface IAdService extends GenericService<Ad, Integer> {

	List<Ad> searchByIndex(String text);

	List<Ad> sellerHistory(Integer sellerID);

	List<Ad> findAdsByCategory(String category, String column);
}
