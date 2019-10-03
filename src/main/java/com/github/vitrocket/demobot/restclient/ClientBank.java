package com.github.vitrocket.demobot.restclient;

import com.github.vitrocket.demobot.restclient.dto.ExchangeRates;
import com.github.vitrocket.demobot.restclient.exception.RestClientException;

public interface ClientBank extends Client {

	ExchangeRates getExchangeRates() throws RestClientException;

}
