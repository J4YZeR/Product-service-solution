package ru.ezatoloka.testsolution.application.csvloader;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import ru.ezatoloka.testsolution.domain.entity.Product;
import ru.ezatoloka.testsolution.domain.entity.ProductPrice;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;


public class CsvToProductsParser {

	public static List<Product> parse(String fileName) throws IOException {
		List<ProductCsv> productsCsv = getProductsCsv(fileName);
		return convertProductsCsvToProducts(productsCsv);
	}

	private static List<ProductCsv> getProductsCsv(String fileName) throws IOException {
		CsvSchema bootstrapSchema = CsvSchema.emptySchema().withColumnSeparator(';').withHeader();
		CsvMapper mapper = CsvMapper.builder().findAndAddModules().build();
		File file = new File(fileName);
		MappingIterator<ProductCsv> readValues =
				mapper.readerFor(ProductCsv.class).with(bootstrapSchema).readValues(file);
		return readValues.readAll();
	}

	private static List<Product> convertProductsCsvToProducts(List<ProductCsv> productsCsv) {
		List<Product> products = new ArrayList<>();
		for (ProductCsv productCsv : productsCsv) {
			Optional<Product> productOptional = findProductById(products, productCsv.getProductId());
			if (productOptional.isPresent()) {
				Product product = productOptional.get();
				product.getProductPrices().add(createProductPrice(productCsv, product));
			} else {
				products.add(createProduct(productCsv));
			}
		}

		return products;
	}

	private static Optional<Product> findProductById(List<Product> products, Long productId) {
		return products.stream().filter(product -> product.getId().equals(productId)).findAny();
	}

	private static ProductPrice createProductPrice(ProductCsv productCsv, Product existingProduct) {
		return new ProductPrice(productCsv.getPriceId(), productCsv.getPrice(), productCsv.getPriceDate(), existingProduct);
	}

	private static Product createProduct(ProductCsv productCsv) {
		Product product = new Product(productCsv.getProductId(), productCsv.getProductName(), new HashSet<>());
		product.getProductPrices().add(createProductPrice(productCsv, product));

		return product;
	}

	static class ProductCsv {

		//		product_id;
		private Long productId;

		//		product_name;
		private String productName;

		//		price_id;
		private Long priceId;

		//		price;
		private BigDecimal price;

		//		price_datetime
		private LocalDate priceDate;

		@JsonProperty("product_id")
		public Long getProductId() {
			return productId;
		}

		public void setProductId(Long productId) {
			this.productId = productId;
		}

		@JsonProperty("product_name")
		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		@JsonProperty("price_id")
		public Long getPriceId() {
			return priceId;
		}

		public void setPriceId(Long priceId) {
			this.priceId = priceId;
		}

		@JsonProperty("price")
		public BigDecimal getPrice() {
			return price;
		}

		public void setPrice(BigDecimal price) {
			this.price = price;
		}

		@JsonProperty("price_datetime")
		public LocalDate getPriceDate() {
			return priceDate;
		}

		public void setPriceDate(LocalDate priceDate) {
			this.priceDate = priceDate;
		}


	}
}
