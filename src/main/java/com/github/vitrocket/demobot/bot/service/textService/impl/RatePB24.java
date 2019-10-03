package com.github.vitrocket.demobot.bot.service.textService.impl;

import com.github.vitrocket.demobot.bot.service.textService.TextMessageService;
import com.github.vitrocket.demobot.domain.ExchangeRate;
import com.github.vitrocket.demobot.restclient.exception.RestClientException;
import com.github.vitrocket.demobot.service.BankService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RatePB24 implements TextMessageService {

	private final BankService bankService;

	@Override
	public String getTextMessage() {
		return getRates();
	}

	public String getRates() {
		List<ExchangeRate> exchangeRates = getExchangeRates();
		if (CollectionUtils.isEmpty(exchangeRates)) {
			return null;
		}
		return exchangeRates.stream()
				.map(e -> "\n\n<b>" + e.getCcy() + "</b> - " + e.getBaseCcy() +
						"\nbuy: " + e.getBuy() +
						"\nsale: " + e.getSale())
				.collect(Collectors.joining());
	}

	private List<ExchangeRate> getExchangeRates() {
		List<ExchangeRate> exchangeRates = null;
		try {
			exchangeRates = bankService.getExchangeRates();
		} catch (RestClientException e) {
			throw new NotFoundException("Can't get Exchange Rates", e);
		}
		return exchangeRates;
	}
}
