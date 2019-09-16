package by.senla.study.api.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import by.senla.study.model.entity.Ad;

public interface IAdService extends GenericService<Ad, Integer> {

	@Transactional
	List<Ad> searchByIndex(String text);
}
