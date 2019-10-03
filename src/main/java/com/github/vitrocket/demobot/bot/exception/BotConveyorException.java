package com.github.vitrocket.demobot.bot.exception;

public class BotConveyorException extends Exception {

	public BotConveyorException(String message, Object object) {
		super(message.replace("{}", String.valueOf(object)));
	}
}
