package com.github.vitrocket.demobot.bot;

import com.github.vitrocket.demobot.bot.exception.BotConveyorException;
import com.github.vitrocket.demobot.util.ContextProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;


@Slf4j
@Component
public class CriptoBot extends TelegramLongPollingBot {

	@Value("${bot.token}")
	private String token;

	@Value("${bot.username}")
	private String username;

	@Override
	public String getBotToken() {
		return token;
	}

	@Override
	public String getBotUsername() {
		return username;
	}

	@Override
	public void onUpdateReceived(Update update) {
		try {
			BotConveyor botConveyor = ContextProvider.getBean(BotConveyor.class);
			botConveyor.botConveyor(update);
		} catch (BotConveyorException e) {
			log.error("PROCREATOR BOT ERROR {}", e.getMessage());
		}
	}
}
