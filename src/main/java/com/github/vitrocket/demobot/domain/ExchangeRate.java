package com.github.vitrocket.demobot.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ExchangeRate {

	private Integer id;

	private ССYCode ccy; // Код валюты

	private ССYCode baseCcy; // Код национальной валюты

	private BigDecimal buy; // Курс покупки

	private BigDecimal sale; // Курс продажи

	private BankName bankName;

	private LocalDateTime created;

}
