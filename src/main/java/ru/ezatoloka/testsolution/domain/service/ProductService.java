package ru.ezatoloka.testsolution.domain.service;

import ru.ezatoloka.testsolution.domain.entity.Product;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface ProductService {

	void saveAll(Set<Product> products);

	List<Product> getAllByDate(LocalDate date);
}
