package ru.ezatoloka.testsolution.domain.service;

import ru.ezatoloka.testsolution.domain.dto.PriceUpdateFrequencyByDateDto;
import ru.ezatoloka.testsolution.domain.dto.PriceUpdateFrequencyByProductNameDto;

import java.util.List;

public interface ProductStatisticsService {

	long getProductsCount();

	List<PriceUpdateFrequencyByProductNameDto> getPriceUpdateFrequenciesByProductName();

	List<PriceUpdateFrequencyByDateDto> getPriceFrequenciesByDate();
}
