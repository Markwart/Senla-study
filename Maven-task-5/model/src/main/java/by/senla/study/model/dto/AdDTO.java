package by.senla.study.model.dto;

import java.math.BigDecimal;
import java.util.Set;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import by.senla.study.model.enums.Status;

public class AdDTO extends BaseDTO {

	private String theme;

	@NotNull
	private String text;

	@PositiveOrZero
	private BigDecimal price;

	private String image;

	private Status status;

	private Integer sellerID;

	private Integer categoryID;

	private Set<Integer> commentsID;

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

	public Integer getSellerID() {
		return sellerID;
	}

	public void setSellerID(Integer sellerID) {
		this.sellerID = sellerID;
	}

	public Integer getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(Integer categoryID) {
		this.categoryID = categoryID;
	}

	public Set<Integer> getCommentsID() {
		return commentsID;
	}

	public void setCommentsID(Set<Integer> commentsID) {
		this.commentsID = commentsID;
	}
}
