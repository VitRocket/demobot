package com.github.vitrocket.demobot.bot.service;

import com.github.vitrocket.demobot.bot.service.messageService.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

@Service
public class MessageConstantImpl implements MessageConstant {

	private static Map<String, String> messageKey = new HashMap<>();

	@PostConstruct
	public void messages() {
		ResourceBundle en = ResourceBundle.getBundle(MESSAGES, MessageService.LOCALE_ENGLISH);
		ResourceBundle ru = ResourceBundle.getBundle(MESSAGES, MessageService.LOCALE_RUSSIAN);

		Enumeration<String> enKeys = en.getKeys();
		Enumeration<String> ruKeys = ru.getKeys();

		while (enKeys.hasMoreElements()) {
			String key = enKeys.nextElement();
			messageKey.put(en.getString(key), key);
		}

		while (ruKeys.hasMoreElements()) {
			String key = ruKeys.nextElement();
			messageKey.put(ru.getString(key), key);
		}
	}

	@Override
	public String getKeyFromMessage(String message) {
		return messageKey.get(message);
	}
}
