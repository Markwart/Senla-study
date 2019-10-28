package by.senla.study.board.model.search;

import java.math.BigDecimal;

public class AdSearchDto {

	private String category;

	private String sortColumn;

	private Boolean ascending;
	
	private String keyword;
	
	private BigDecimal priceStart;
	
	private BigDecimal priceFinal;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSortColumn() {
		return sortColumn;
	}

	public void setSortColumn(String sortColumn) {
		this.sortColumn = sortColumn;
	}

	public Boolean getAscending() {
		return ascending;
	}

	public void setAscending(Boolean ascending) {
		this.ascending = ascending;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public BigDecimal getPriceStart() {
		return priceStart;
	}

	public void setPriceStart(BigDecimal priceStart) {
		this.priceStart = priceStart;
	}

	public BigDecimal getPriceFinal() {
		return priceFinal;
	}

	public void setPriceFinal(BigDecimal priceFinal) {
		this.priceFinal = priceFinal;
	}
}
