package com.github.vitrocket.demobot.bot;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface Sender {
	Message sendMessage(SendMessage sendMessage);
}
