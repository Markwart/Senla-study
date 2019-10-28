package by.senla.study.board.model.dto;

import javax.validation.constraints.NotNull;

public class CategoryDto extends BaseDto {

	@NotNull
	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
