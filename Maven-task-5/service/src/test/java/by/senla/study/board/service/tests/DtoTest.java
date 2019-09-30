package by.senla.study.board.service.tests;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import by.senla.study.board.model.dto.AdDto;
import by.senla.study.board.model.dto.CategoryDto;
import by.senla.study.board.model.entity.Ad;
import by.senla.study.board.model.entity.Category;

public class DtoTest extends AbstractTest {

	@Test
	public void test1() {
		Category entity = new Category();
		entity.setId(RANDOM_INT);
		entity.setName(RANDOM_STRING);
		entity.setCreated(new Date());
		entity.setUpdated(new Date());
		
		Set<Ad> ads = new HashSet<>();
		for (int id = 1; id <= 10; id++) {
			Ad ad = createAdEntity();
			ad.setId(id);
			ads.add(ad);
		}
		entity.setAds(ads);
		
		CategoryDto dto = modelMapper.map(entity, CategoryDto.class);
		assertEquals(entity.getId(), dto.getId());
		assertEquals(entity.getName(), dto.getName());
		assertEquals(entity.getCreated(), dto.getCreated());
		assertEquals(entity.getUpdated(), dto.getUpdated());
		assertEquals(entity.getAds().size(), dto.getAds().size());
	}

	@Test
	public void test2() {
		CategoryDto dto = new CategoryDto();
		dto.setId(RANDOM_INT);
		dto.setName(RANDOM_STRING);
		dto.setCreated(new Date());
		dto.setUpdated(new Date());
		
		Set<AdDto> adsDto = new HashSet<>();
		for (int id = 1; id <= 10; id++) {
			AdDto adDto = createAdDto();
			adDto.setId(id);
			adsDto.add(adDto);
		}
		dto.setAds(adsDto);

		Category entity = modelMapper.map(dto, Category.class);
		assertEquals(dto.getId(), entity.getId());
		assertEquals(dto.getName(), entity.getName());
		assertEquals(dto.getCreated(), entity.getCreated());
		assertEquals(dto.getUpdated(), entity.getUpdated());
		assertEquals(entity.getAds().size(), dto.getAds().size());
	}
}