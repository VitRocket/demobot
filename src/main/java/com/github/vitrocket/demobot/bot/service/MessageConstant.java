package com.github.vitrocket.demobot.bot.service;

public interface MessageConstant {

	String MESSAGES = "messages";

	String MESSAGE_SUBSCRIBE_DONE = "message.subscribe.done";
	String MESSAGE_UNSUBSCRIBE_DONE = "message.unsubscribe.done";
	String BUTTON_SUBSCRIBE = "button.subscribe";
	String BUTTON_UNSUBSCRIBE = "button.unsubscribe";
	String BUTTON_EXCHANGE_SUBSCRIBE_COMMAND = "button.exchange.subscribe.command";
	String MESSAGE_EXCHANGE_RATES_TITLE = "message.exchange.rates.title";
	String BUTTON_EXCHANGE_UNSUBSCRIBE_COMMAND = "button.exchange.unsubscribe.command";
	String MESSAGE_START_HELLO = "message.start.hello";
	String MESSAGE_ABOUT_MESSAGE = "message.about.message";
	String MESSAGE_START_MESSAGE = "message.start.message";
	String MAIN_MENU_START = "main.menu.start";
	String MAIN_MENU_ABOUT = "main.menu.about";
	String MAIN_MENU_EXCHANGE_RATES = "main.menu.exchange.rates";

	String SPACE = " ";
	String POINT = ".";

	String getKeyFromMessage(String message);

}
