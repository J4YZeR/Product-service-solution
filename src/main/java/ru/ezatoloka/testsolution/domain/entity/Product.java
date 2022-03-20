package ru.ezatoloka.testsolution.domain.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Product {

	@Id
	private Long id;

	private String name;

	@JsonManagedReference
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<ProductPrice> productPrices;

	public Product() { }

	public Product(Long id, String name, Set<ProductPrice> productPrices) {
		this.id = id;
		this.name = name;
		this.productPrices = productPrices;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<ProductPrice> getProductPrices() {
		return productPrices;
	}

	public void setProductPrices(Set<ProductPrice> productPrices) {
		this.productPrices = productPrices;
	}
}
