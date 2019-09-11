package by.senla.study.api.service;

import java.util.List;

import by.senla.study.model.entity.Ad;

public interface IAdService extends GenericService<Ad, Integer> {

	Ad getFullInfo(Integer id);

	List<Ad> searchByIndex(String text);
}
