package by.senla.study.board.model.dto;

import java.math.BigDecimal;

import javax.validation.constraints.PositiveOrZero;

import by.senla.study.board.model.enums.Status;

public class AdDto extends BaseDto {

	private String theme;

	private String text;

	@PositiveOrZero
	private BigDecimal price;

	private String image;

	private Status status;

	private Integer sellerId;

	private Integer categoryId;
	
	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
}
