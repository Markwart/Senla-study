package by.senla.study.service.converterDTO;

import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import by.senla.study.model.dto.CategoryDTO;
import by.senla.study.model.entity.Ad;
import by.senla.study.model.entity.Category;

@Component
public class CategoryFromDTO implements Function<CategoryDTO, Category> {

	@Override
	public Category apply(CategoryDTO dto) {
		Category entity = new Category();
		entity.setId(dto.getId());
		entity.setName(dto.getName());

		Set<Integer> ads = dto.getAdsID();
		if (!ads.isEmpty()) {
			entity.setAds(ads.stream().map((id) -> {
				Ad ad = new Ad();
				ad.setId(id);
				return ad;
			}).collect(Collectors.toSet()));
		}
		return entity;
	}
}
