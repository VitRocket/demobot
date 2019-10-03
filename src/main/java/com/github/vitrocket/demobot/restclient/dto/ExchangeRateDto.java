package com.github.vitrocket.demobot.restclient.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.vitrocket.demobot.domain.ССYCode;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ExchangeRateDto {

	private ССYCode ccy; // Код валюты

	@JsonProperty("base_ccy")
	private ССYCode baseCcy; // Код национальной валюты

	private BigDecimal buy; // Курс покупки

	private BigDecimal sale; // Курс продажи

}
