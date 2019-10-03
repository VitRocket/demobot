package com.github.vitrocket.demobot.bot.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainMenu {

	private ResourceBundle bundle;

	public MainMenu(ResourceBundle bundle) {
		this.bundle = bundle;
	}

	public ReplyKeyboardMarkup invoke() {
		ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
		keyboardMarkup.setResizeKeyboard(true);
		List<KeyboardRow> keyboard = new ArrayList<>();
		KeyboardRow keyboardRow = new KeyboardRow();

		KeyboardButton keyboardButton = new KeyboardButton();
		keyboardButton.setText(bundle.getString("main.menu.start"));
		keyboardRow.add(keyboardButton);

		keyboardButton = new KeyboardButton();
		keyboardButton.setText(bundle.getString("main.menu.about"));
		keyboardRow.add(keyboardButton);

		keyboard.add(keyboardRow);
		keyboardRow = new KeyboardRow();
		keyboardRow.add(bundle.getString("main.menu.exchange.rates"));
		keyboard.add(keyboardRow);
		keyboardMarkup.setKeyboard(keyboard);
		return keyboardMarkup;
	}
}