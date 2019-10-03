package com.github.vitrocket.demobot.bot.service.messageService;

import com.github.vitrocket.demobot.domain.Subscribe;
import com.github.vitrocket.demobot.domain.TUser;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import static com.github.vitrocket.demobot.bot.service.MessageConstant.MESSAGES;

public interface MessageService {

	String LANGUAGE_EN = "en";
	String LANGUAGE_RU = "ru";
	String COUNTRY_UK = "UK";
	String COUNTRY_RU = "RU";

	Locale LOCALE_ENGLISH = new Locale(LANGUAGE_EN, COUNTRY_UK);
	Locale LOCALE_RUSSIAN = new Locale(LANGUAGE_RU, COUNTRY_RU);

	default Locale getLocale(String languageCode) {
		Locale locale;
		if ("ru".equals(languageCode)) {
			locale = LOCALE_RUSSIAN;
		} else {
			locale = LOCALE_ENGLISH;
		}
		return locale;
	}

	default ResourceBundle getBundle(String languageCode) {
		return ResourceBundle.getBundle(MESSAGES, getLocale(languageCode));
	}

	default TUser tUserFetFromUser(User user) {
		TUser tUser = new TUser(user.getId(), user.getFirstName(), user.getLanguageCode(), user.getBot());
		Optional.ofNullable(user.getLastName()).ifPresent(tUser::setLastName);
		Optional.ofNullable(user.getUserName()).ifPresent(tUser::setUserName);
		return tUser;
	}

	SendMessage generateMessage(Update update);

	List<SendMessage> generateMessage(Subscribe subscribe) throws MessageServiceException;
}
