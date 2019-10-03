package com.github.vitrocket.demobot.bot.service.button;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@Service
public class ButtonServiceImpl implements ButtonService {

	@Override
	public InlineKeyboardMarkup getButton(ResourceBundle bundle, String title, String command) {
		InlineKeyboardMarkup markupInline = new InlineKeyboardMarkup();
		List<List<InlineKeyboardButton>> rowsInline = new ArrayList<>();
		List<InlineKeyboardButton> rowInline = new ArrayList<>();
		rowInline.add(new InlineKeyboardButton()
				.setText(bundle.getString(title))
				.setCallbackData(bundle.getString(command)));
		rowsInline.add(rowInline);
		markupInline.setKeyboard(rowsInline);
		return markupInline;
	}
}
