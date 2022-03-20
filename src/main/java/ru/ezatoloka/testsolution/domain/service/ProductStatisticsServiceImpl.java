package ru.ezatoloka.testsolution.domain.service;

import org.springframework.stereotype.Service;
import ru.ezatoloka.testsolution.domain.repository.ProductPriceRepository;
import ru.ezatoloka.testsolution.domain.repository.ProductRepository;
import ru.ezatoloka.testsolution.domain.dto.PriceUpdateFrequencyByDateDto;
import ru.ezatoloka.testsolution.domain.dto.PriceUpdateFrequencyByProductNameDto;

import java.util.List;

@Service
public class ProductStatisticsServiceImpl implements ProductStatisticsService {

	private final ProductRepository productRepository;
	private final ProductPriceRepository productPriceRepository;

	public ProductStatisticsServiceImpl(ProductRepository productRepository, ProductPriceRepository productPriceRepository) {
		this.productRepository = productRepository;
		this.productPriceRepository = productPriceRepository;
	}

	@Override
	public long getProductsCount() {
		return productRepository.count();
	}

	@Override
	public List<PriceUpdateFrequencyByProductNameDto> getPriceUpdateFrequenciesByProductName() {
		return productRepository.countProductPriceUpdatesGroupByName();
	}

	@Override
	public List<PriceUpdateFrequencyByDateDto> getPriceFrequenciesByDate() {
		return productPriceRepository.countProductPriceUpdatesGroupByDate();
	}

}
