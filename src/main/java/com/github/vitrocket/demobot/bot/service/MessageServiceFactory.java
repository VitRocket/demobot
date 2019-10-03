package com.github.vitrocket.demobot.bot.service;

import com.github.vitrocket.demobot.bot.service.messageService.MessageService;

public interface MessageServiceFactory {

	MessageService getMessageService(String type);

}
