package com.github.vitrocket.demobot.domain;

import com.github.vitrocket.demobot.bot.service.MessageConstant;

import java.util.HashMap;
import java.util.Map;

public class SubscribeAbstract {

	private static Map<ScribeAction, String> subscribeMessageTitles = new HashMap<>(2);

	private static Map<ScribeAction, String> unsubscribeCommands = new HashMap<>(2);

	static {
		subscribeMessageTitles.put(ScribeAction.ratePB24, MessageConstant.MESSAGE_EXCHANGE_RATES_TITLE);
	}

	static {
		unsubscribeCommands.put(ScribeAction.ratePB24, MessageConstant.BUTTON_EXCHANGE_UNSUBSCRIBE_COMMAND);
	}

	public String getTitleMessage(ScribeAction action) {
		return subscribeMessageTitles.get(action);
	}

	public String getUnsubscribeCommand(ScribeAction action) {
		return unsubscribeCommands.get(action);
	}
}
