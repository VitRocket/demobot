package com.github.vitrocket.demobot.restclient;

import com.github.vitrocket.demobot.restclient.dto.ExchangeRateDto;
import com.github.vitrocket.demobot.restclient.dto.ExchangeRates;
import com.github.vitrocket.demobot.restclient.exception.RestClientException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientBankPrivat24 implements ClientBank {

	private static final String API_PRIVATBANK_EXCHANGE_COURSID_5 = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";
	private final RestTemplate restTemplate;

	@Override
	public ExchangeRates getExchangeRates() throws RestClientException {
		ResponseEntity<List<ExchangeRateDto>> response = getResponse(getUri(API_PRIVATBANK_EXCHANGE_COURSID_5));
		ExchangeRates exchangeRates = new ExchangeRates();
		exchangeRates.setExchangeRateDtos(response.getBody());
		return exchangeRates;
	}

	private ResponseEntity<List<ExchangeRateDto>> getResponse(URI uri) {
		return restTemplate.exchange(
				uri,
				HttpMethod.GET,
				null,
				new ParameterizedTypeReference<List<ExchangeRateDto>>() {
				});
	}
}
