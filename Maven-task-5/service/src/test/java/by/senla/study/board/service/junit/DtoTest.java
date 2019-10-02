package by.senla.study.board.service.junit;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import by.senla.study.board.model.dto.AdDto;
import by.senla.study.board.model.dto.CategoryDto;
import by.senla.study.board.model.entity.Ad;
import by.senla.study.board.model.entity.Category;
import by.senla.study.board.model.enums.Status;

public class DtoTest extends AbstractTest {
	
	private ModelMapper modelMapper = new ModelMapper();

	@Test
	public void test1() {
		Category entity = new Category();
		entity.setId(getRandomInt());
		entity.setName(getRandomString());
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
		dto.setId(getRandomInt());
		dto.setName(getRandomString());
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
	
	public Ad createAdEntity() {
		Ad ad = new Ad();
		ad.setId(getRandomInt());
		ad.setImage(getRandomString());
		ad.setPrice(new BigDecimal(getRandomInt()));
		ad.setStatus(Status.OPEN);
		ad.setText(getRandomString());
		ad.setTheme(getRandomString());
		ad.setCreated(new Date());
		ad.setUpdated(new Date());
		return ad;
	}

	public AdDto createAdDto() {
		AdDto adDto = new AdDto();
		adDto.setId(getRandomInt());
		adDto.setImage(getRandomString());
		adDto.setPrice(new BigDecimal(getRandomInt()));
		adDto.setStatus(Status.OPEN);
		adDto.setText(getRandomString());
		adDto.setTheme(getRandomString());
		adDto.setCreated(new Date());
		adDto.setUpdated(new Date());
		return adDto;
	}
}