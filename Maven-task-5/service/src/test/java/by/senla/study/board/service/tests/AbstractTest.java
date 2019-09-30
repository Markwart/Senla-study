package by.senla.study.board.service.tests;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.modelmapper.ModelMapper;

import by.senla.study.board.model.dto.AdDto;
import by.senla.study.board.model.entity.Ad;
import by.senla.study.board.model.enums.Status;

public abstract class AbstractTest {

	protected ModelMapper modelMapper = new ModelMapper();
	protected static final Integer RANDOM_INT = new Random().nextInt(100) + 1;
	protected static final String RANDOM_STRING = UUID.randomUUID().toString();

	public Ad createAdEntity() {
		Ad ad = new Ad();
		ad.setId(RANDOM_INT);
		ad.setImage(RANDOM_STRING);
		ad.setPrice(new BigDecimal(RANDOM_INT));
		ad.setStatus(Status.OPEN);
		ad.setText(RANDOM_STRING);
		ad.setTheme(RANDOM_STRING);
		ad.setCreated(new Date());
		ad.setUpdated(new Date());
		return ad;
	}
	
	public AdDto createAdDto() {
		AdDto adDto = new AdDto();
		adDto.setId(RANDOM_INT);
		adDto.setImage(RANDOM_STRING);
		adDto.setPrice(new BigDecimal(RANDOM_INT));
		adDto.setStatus(Status.OPEN);
		adDto.setText(RANDOM_STRING);
		adDto.setTheme(RANDOM_STRING);
		adDto.setCreated(new Date());
		adDto.setUpdated(new Date());
		return adDto;
	}
}
