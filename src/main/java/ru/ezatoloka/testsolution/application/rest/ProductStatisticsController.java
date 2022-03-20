package ru.ezatoloka.testsolution.application.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ezatoloka.testsolution.domain.dto.PriceUpdateFrequencyByDateDto;
import ru.ezatoloka.testsolution.domain.dto.PriceUpdateFrequencyByProductNameDto;
import ru.ezatoloka.testsolution.domain.service.ProductStatisticsService;

import java.util.List;

@RestController
@RequestMapping("/products/statistics")
public class ProductStatisticsController {

	private final ProductStatisticsService productStatisticsService;

	public ProductStatisticsController(ProductStatisticsService productStatisticsService) {
		this.productStatisticsService = productStatisticsService;
	}

	@GetMapping("/count")
	public long getProductsCount() {
		return productStatisticsService.getProductsCount();
	}

	@GetMapping("/frequencies-by-name")
	public List<PriceUpdateFrequencyByProductNameDto> getFrequenciesByName() {
		return productStatisticsService.getPriceUpdateFrequenciesByProductName();
	}

	@GetMapping("/frequencies-by-date")
	public List<PriceUpdateFrequencyByDateDto> getFrequenciesByDate() {
		return productStatisticsService.getPriceFrequenciesByDate();
	}


}
