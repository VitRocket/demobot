package com.github.vitrocket.demobot.service;

import com.github.vitrocket.demobot.domain.ExchangeRate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {


	public List<ExchangeRate> getLastExchangesRates() {
		return new ArrayList<>();
	}

	@Override
	public void saveAll(List<ExchangeRate> list) {

	}

}
