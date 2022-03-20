package ru.ezatoloka.testsolution.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.ezatoloka.testsolution.domain.entity.Product;
import ru.ezatoloka.testsolution.domain.repository.ProductRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> getAllByDate(LocalDate date) {
		return StreamSupport.stream(productRepository.findDistinctByProductPrices_Datetime(date).spliterator(), false).collect(Collectors.toList());
	}

	@Override
	public void saveAll(Set<Product> products) {
		productRepository.saveAll(products);

		logSavedEntriesCount(products);
	}

	private void logSavedEntriesCount(Set<Product> products) {
		int savedProductsCount = products.size();
		int savedProductPricesCount = products.stream().map(product -> product.getProductPrices().size()).reduce(0, Integer::sum);
		log.info("Saved {} products and {} product prices", savedProductsCount, savedProductPricesCount);
	}

}
