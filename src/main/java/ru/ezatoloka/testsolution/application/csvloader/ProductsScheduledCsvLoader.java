package ru.ezatoloka.testsolution.application.csvloader;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;
import ru.ezatoloka.testsolution.config.CustomConfigProperties;
import ru.ezatoloka.testsolution.domain.entity.Product;
import ru.ezatoloka.testsolution.domain.service.ProductService;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class ProductsScheduledCsvLoader {

	private final CustomConfigProperties.CsvLoader csvLoaderProperties;
	private final TaskScheduler taskScheduler;
	private final ProductService productService;

	public ProductsScheduledCsvLoader(CustomConfigProperties customConfigProperties, TaskScheduler taskScheduler, ProductService productService) {
		this.csvLoaderProperties = customConfigProperties.getCsvLoader();
		this.taskScheduler = taskScheduler;
		this.productService = productService;
	}

	@PostConstruct
	public void run() {
		taskScheduler.scheduleAtFixedRate(this::loadProductsFromCsv, csvLoaderProperties.getInitialReadDate(), csvLoaderProperties.getReadRate());
	}

	private void loadProductsFromCsv() {
		try {
			Set<Product> products = getProductsFromCsv(csvLoaderProperties.getPath());
			saveProducts(products);
			deleteCsv(csvLoaderProperties.getPath());
		} catch (IOException exception) {
			log.error("Error occurred while reading csv file with path {}", csvLoaderProperties.getPath());
			System.err.println(exception.getMessage());
		}
	}

	private Set<Product> getProductsFromCsv(String path) throws IOException {
		log.info("Started to get products from csv with path {}", path);
		Set<Product> products = new HashSet<>(CsvToProductsParser.parse(path));
		return products;
	}

	private void deleteCsv(String path) {
		log.info("Deleting csv with path {}", path);
		File file = new File(path);
		if (file.delete()) {
			log.info("Deleted file successfully");
		} else {
			log.warn("Cannot delete file");
		}
	}

	private void saveProducts(Set<Product> products) {
		productService.saveAll(products);
	}
}
