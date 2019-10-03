package com.github.vitrocket.demobot.restclient.dto;

import lombok.Data;

import java.util.List;

@Data
public class ExchangeRates {

	private List<ExchangeRateDto> exchangeRateDtos;

}
