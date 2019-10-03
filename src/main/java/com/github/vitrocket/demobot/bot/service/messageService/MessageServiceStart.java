package com.github.vitrocket.demobot.bot.service.messageService;

import com.github.vitrocket.demobot.bot.service.MainMenu;
import com.github.vitrocket.demobot.domain.Subscribe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.ResourceBundle;

import static com.github.vitrocket.demobot.bot.service.MessageConstant.*;

@Slf4j
@Service
public class MessageServiceStart implements MessageService {

	@Override
	public SendMessage generateMessage(Update update) {
		ResourceBundle bundle = getBundle(update.getMessage().getFrom().getLanguageCode());
		String text = getStartMessage(update, bundle);
		return getSendMessage(update, bundle, text);
	}

	private String getStartMessage(Update update, ResourceBundle bundle) {
		return bundle.getString(MESSAGE_START_HELLO) + SPACE +
				update.getMessage().getChat().getFirstName() +
				SPACE +
				bundle.getString(MESSAGE_START_MESSAGE) +
				POINT;
	}

	private SendMessage getSendMessage(Update update, ResourceBundle bundle, String text) {
		return new SendMessage()
				.setChatId(update.getMessage().getChatId())
				.enableHtml(true)
				.setText(text)
				.setReplyMarkup(new MainMenu(bundle).invoke());
	}

	@Override
	public List<SendMessage> generateMessage(Subscribe subscribe) {
		return null;
	}
}
