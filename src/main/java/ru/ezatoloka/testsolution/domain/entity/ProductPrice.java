package ru.ezatoloka.testsolution.domain.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class ProductPrice {

	@Id
	private Long id;

	private BigDecimal price;

	private LocalDate datetime;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_id")
	private Product product;

	public ProductPrice() {
	}

	public ProductPrice(Long id, BigDecimal price, LocalDate datetime, Product product) {
		this.id = id;
		this.price = price;
		this.datetime = datetime;
		this.product = product;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public LocalDate getDatetime() {
		return datetime;
	}

	public void setDatetime(LocalDate datetime) {
		this.datetime = datetime;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
