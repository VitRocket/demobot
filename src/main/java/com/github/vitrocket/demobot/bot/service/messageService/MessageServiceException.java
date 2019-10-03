package com.github.vitrocket.demobot.bot.service.messageService;

public class MessageServiceException extends Exception {

	MessageServiceException(String message, Object object) {
		super(message.replace("{}", String.valueOf(object)));
	}
}
