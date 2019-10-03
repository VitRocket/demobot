package com.github.vitrocket.demobot.bot.service.button;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.ResourceBundle;

public interface ButtonService {

	InlineKeyboardMarkup getButton(ResourceBundle bundle, String title, String command);
}
