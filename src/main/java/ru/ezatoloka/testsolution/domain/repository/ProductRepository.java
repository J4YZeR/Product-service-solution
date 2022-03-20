package ru.ezatoloka.testsolution.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.ezatoloka.testsolution.domain.dto.PriceUpdateFrequencyByProductNameDto;
import ru.ezatoloka.testsolution.domain.entity.Product;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	Iterable<Product> findDistinctByProductPrices_Datetime(LocalDate date);

	@Query("SELECT new ru.ezatoloka.testsolution.domain.dto.PriceUpdateFrequencyByProductNameDto(p.name, COUNT(pr.id)) "
			+ "FROM Product AS p JOIN p.productPrices pr GROUP BY p.name")
	List<PriceUpdateFrequencyByProductNameDto> countProductPriceUpdatesGroupByName();


}
