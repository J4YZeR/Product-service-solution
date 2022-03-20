package ru.ezatoloka.testsolution.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PriceUpdateFrequencyByProductNameDto {

	private String name;

	private Long frequency;
}
