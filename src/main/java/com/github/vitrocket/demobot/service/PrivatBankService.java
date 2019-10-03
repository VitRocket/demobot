package com.github.vitrocket.demobot.service;

import com.github.vitrocket.demobot.domain.BankName;
import com.github.vitrocket.demobot.domain.ExchangeRate;
import com.github.vitrocket.demobot.restclient.ClientBank;
import com.github.vitrocket.demobot.restclient.dto.ExchangeRateDto;
import com.github.vitrocket.demobot.restclient.dto.ExchangeRates;
import com.github.vitrocket.demobot.restclient.exception.RestClientException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrivatBankService implements BankService {

	private final ExchangeRateService exchangeRateService;
	private final ClientBank clientBank;

	@Override
	public List<ExchangeRate> getExchangeRates() throws RestClientException {
		List<ExchangeRate> list = exchangeRateService.getLastExchangesRates();
		if (isExchangeRateOld(list)) {
			ExchangeRates result = clientBank.getExchangeRates();
			list = result.getExchangeRateDtos().stream()
					.map(this::defineFromDto)
					.collect(Collectors.toList());
			exchangeRateService.saveAll(list);
		}
		return list;
	}

	private boolean isExchangeRateOld(List<ExchangeRate> list) {
		if (!CollectionUtils.isEmpty(list)) {
			return list.stream()
					.anyMatch(exchangeRate -> exchangeRate.getCreated().isBefore(LocalDateTime.now().plusHours(1L)));
		} else {
			return true;
		}
	}

	private ExchangeRate defineFromDto(ExchangeRateDto dto) {
		ExchangeRate exchangeRate = new ExchangeRate();
		BeanUtils.copyProperties(dto, exchangeRate);
		exchangeRate.setBankName(BankName.P24);
		return exchangeRate;
	}
}
