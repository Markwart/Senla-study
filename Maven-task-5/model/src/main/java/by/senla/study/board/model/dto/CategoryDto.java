package by.senla.study.board.model.dto;

import java.util.Set;

import javax.validation.constraints.NotNull;

public class CategoryDto extends BaseDto {

	@NotNull
	private String name;

	private Set<AdDto> ads;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<AdDto> getAds() {
		return ads;
	}

	public void setAds(Set<AdDto> ads) {
		this.ads = ads;
	}
}
