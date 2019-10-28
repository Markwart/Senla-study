package by.senla.study.board.api.dao;


import java.util.List;

import by.senla.study.board.model.entity.Ad;
import by.senla.study.board.model.search.AdSearchDto;

public interface IAdDao extends GenericDao<Ad, Integer> {

	List<Ad> searchByIndex(String keyword);

	List<Ad> sellerHistory(Integer sellerID);

	List<Ad> filterAds(AdSearchDto dto);
}
