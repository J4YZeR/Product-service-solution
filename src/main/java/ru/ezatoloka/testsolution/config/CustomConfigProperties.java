package ru.ezatoloka.testsolution.config;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "custom")
public class CustomConfigProperties {

	private CsvLoader csvLoader;

	@Getter
	@Setter
	public static class CsvLoader {

		private String path;

		private Date initialReadDate = Date.from(Instant.now());

		private Long readRate = 100000L;

	}

	@Component
	@ConfigurationPropertiesBinding
	public static class DateConverter implements Converter<String, Date> {
		@SneakyThrows
		@Override
		public Date convert(String source) {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(source);
		}
	}
}
