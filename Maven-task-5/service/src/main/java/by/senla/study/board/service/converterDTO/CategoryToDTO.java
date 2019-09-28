package by.senla.study.board.service.converterDTO;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import by.senla.study.board.api.DTOConverter.IToDTOConverter;
import by.senla.study.board.model.dto.CategoryDTO;
import by.senla.study.board.model.entity.Ad;
import by.senla.study.board.model.entity.Category;

@Component
public class CategoryToDTO implements IToDTOConverter<Category, CategoryDTO> {

	@Override
	public CategoryDTO apply(Category entity) {
		CategoryDTO dto = new CategoryDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setCreated(entity.getCreated());
		dto.setUpdated(entity.getUpdated());

		Set<Ad> ads = entity.getAds();
		if (!ads.isEmpty()) {
			dto.setAdsID(ads.stream().map(Ad::getId).collect(Collectors.toSet()));
		}
		return dto;
	}
}
