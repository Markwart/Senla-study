package by.senla.study.model.dto;

import java.util.Set;

import javax.validation.constraints.NotNull;

public class CategoryDTO extends BaseDTO {

	@NotNull
	private String name;

	private Set<Integer> adsID;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Integer> getAdsID() {
		return adsID;
	}

	public void setAdsID(Set<Integer> adsID) {
		this.adsID = adsID;
	}

}
