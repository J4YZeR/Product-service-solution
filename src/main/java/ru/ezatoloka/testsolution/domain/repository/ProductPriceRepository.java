package ru.ezatoloka.testsolution.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ezatoloka.testsolution.domain.dto.PriceUpdateFrequencyByDateDto;
import ru.ezatoloka.testsolution.domain.entity.ProductPrice;

import java.util.List;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, Long> {

	@Query("SELECT new ru.ezatoloka.testsolution.domain.dto.PriceUpdateFrequencyByDateDto(pr.datetime, COUNT(p.id)) "
			+ "FROM ProductPrice AS pr JOIN pr.product p GROUP BY pr.datetime")
	List<PriceUpdateFrequencyByDateDto> countProductPriceUpdatesGroupByDate();
}
