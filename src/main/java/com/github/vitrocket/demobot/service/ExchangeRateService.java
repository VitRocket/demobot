package com.github.vitrocket.demobot.service;

import com.github.vitrocket.demobot.domain.ExchangeRate;

import java.util.List;

public interface ExchangeRateService {

	List<ExchangeRate> getLastExchangesRates();

	void saveAll(List<ExchangeRate> list);
}
