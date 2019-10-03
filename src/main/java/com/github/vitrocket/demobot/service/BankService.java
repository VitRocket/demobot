package com.github.vitrocket.demobot.service;

import com.github.vitrocket.demobot.domain.ExchangeRate;
import com.github.vitrocket.demobot.restclient.exception.RestClientException;

import java.util.List;

public interface BankService {

	List<ExchangeRate> getExchangeRates() throws RestClientException;

}
