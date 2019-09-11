package by.senla.study.model.entity;

import java.math.BigDecimal;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import by.senla.study.model.enums.Status;

@Entity
@Table(name = "ad")
@Indexed
public class Ad extends BaseEntity {

	@Column(name = "theme")
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private String theme;

	@Column(name = "text")
	@Field(index = Index.YES, analyze = Analyze.YES, store = Store.NO)
	private String text;

	@Column(name = "price")
	private BigDecimal price;

	@Column(name = "image")
	private String image;

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private Status status;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "seller_id")
	private UserAccount seller;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id")
	private Category category;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "ad")
	private Set<Comment> comments = new HashSet<Comment>();

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

	public UserAccount getSeller() {
		return seller;
	}

	public void setSeller(UserAccount seller) {
		this.seller = seller;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

}
