package com.github.vitrocket.demobot.bot;

import com.github.vitrocket.demobot.bot.exception.BotConveyorException;
import com.github.vitrocket.demobot.bot.service.MessageServiceFactory;
import com.github.vitrocket.demobot.bot.service.messageService.MessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Service
@RequiredArgsConstructor
public class BotConveyorImpl implements BotConveyor {

	private final MessageServiceFactory messageServiceFactory;
	private final Sender sender;

	@Override
	public void botConveyor(Update update) throws BotConveyorException {
		if (update.hasMessage()) {
			generateFromMessage(update);
		} else if (update.hasChannelPost()) {
			generateFromChannelPost(update);
		} else if (update.hasCallbackQuery()) {
			generateFromCallbackQuery(update);
		} else {
			throw new BotConveyorException("Doesn't define BotApiMethod, update_id: {}", update.getUpdateId());
		}
	}

	private void generateFromMessage(Update update) {
		if (update.hasMessage() && update.getMessage().hasText()) {
			MessageService service = messageServiceFactory.getMessageService(update.getMessage().getText());
			if (update.getMessage().hasLocation()) {
				log.info("СОДЕРЖИТ ЛОКАЦИЮ");
			}
			sender.sendMessage(service.generateMessage(update));
		} else if (update.hasCallbackQuery()) {
			log.info("WTF");
		} else if (update.getMessage().hasLocation()) {
			Message message = update.getMessage().getReplyToMessage();
			if (message != null) {
				if (update.getMessage().getReplyToMessage().hasText()) {
					MessageService service = messageServiceFactory.getMessageService(update.getMessage().getReplyToMessage().getText());
					sender.sendMessage(service.generateMessage(update));
				}
			} else {
				log.error("Не определяет");
			}
		} else if (update.getMessage().hasContact()) {
			log.info(update.getMessage().getContact().toString());
			MessageService service = messageServiceFactory.getMessageService(update.getMessage().getText());
			sender.sendMessage(service.generateMessage(update));
		}
	}

	private void generateFromChannelPost(Update update) {
		log.info("Need to do");
	}

	//Ответ из сообщения
	private void generateFromCallbackQuery(Update update) {
		MessageService service = messageServiceFactory.getMessageService(update.getCallbackQuery().getData());
		sender.sendMessage(service.generateMessage(update));
	}
}
