package com.github.vitrocket.demobot.bot;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Slf4j
@Service
@RequiredArgsConstructor
public class SenderImpl implements Sender {

	private final CriptoBot criptoBot;

	@Override
	public Message sendMessage(SendMessage sendMessage) {
		try {
			return criptoBot.execute(sendMessage);
		} catch (TelegramApiException e) {
			log.error("BOT ERROR {}", e.getMessage());
			return null;
		}
	}
}
