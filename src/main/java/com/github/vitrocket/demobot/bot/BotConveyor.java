package com.github.vitrocket.demobot.bot;

import com.github.vitrocket.demobot.bot.exception.BotConveyorException;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface BotConveyor {

	void botConveyor(Update update) throws BotConveyorException;
}
