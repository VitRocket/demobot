package com.github.vitrocket.demobot.bot.service.messageService;

import com.github.vitrocket.demobot.bot.service.MessageConstant;
import com.github.vitrocket.demobot.bot.service.button.ButtonService;
import com.github.vitrocket.demobot.bot.service.textService.TextMessageContext;
import com.github.vitrocket.demobot.bot.service.textService.TextMessageService;
import com.github.vitrocket.demobot.domain.Subscribe;
import com.github.vitrocket.demobot.domain.TUser;
import com.github.vitrocket.demobot.service.SubscribeService;
import com.github.vitrocket.demobot.util.ContextProvider;
import lombok.RequiredArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class MessageServiceAbstract implements MessageService {

	private Map<String, BiFunction<Update, ResourceBundle, SendMessage>> methodFactory = new HashMap<>(2);

	{
		methodFactory.put(MessageConstant.BUTTON_EXCHANGE_SUBSCRIBE_COMMAND, this::getSubscribe);
		methodFactory.put(MessageConstant.BUTTON_EXCHANGE_UNSUBSCRIBE_COMMAND, this::getUnSubscribe);
	}

	private final TextMessageContext textMessageContext;
	protected final ButtonService buttonService;
	private final MessageConstant messageConstant;
	protected final SubscribeService subscribeService;

	public SendMessage generateMessage(Update update) {
		if (update.hasMessage()) {
			return defineSendMessage(update);
		} else if (update.hasCallbackQuery()) {
			return defineEditMessage(update);
		} else {
			return null;
		}
	}

	@Override
	public List<SendMessage> generateMessage(Subscribe subscribe) throws MessageServiceException {
		textMessageContext.setTextMessageService((TextMessageService) ContextProvider.getBean(subscribe.getScribeAction().name()));

		String message = textMessageContext.getTextMessage();
		Map<String, String> titles = getTitlesMessage(subscribe.getTitleMessage(subscribe.getScribeAction()));
		Map<String, ReplyKeyboard> buttons = getUnSubscribeButtons(buttonService, subscribe.getUnsubscribeCommand(subscribe.getScribeAction()));

		if (Objects.isNull(message)) {
			throw new MessageServiceException("error send: {}", subscribe.getId());
		}
		return subscribe.getTUsers().stream().map(tUser -> generateMessage(tUser, message, titles, buttons)).collect(Collectors.toList());
	}

	private SendMessage defineSendMessage(Update update) {
		ResourceBundle bundle = getBundle(update.getMessage().getFrom().getLanguageCode());
		return getSendMessage(update, bundle);
	}

	private SendMessage defineEditMessage(Update update) {
		ResourceBundle bundle = getBundle(update.getCallbackQuery().getFrom().getLanguageCode());
		messageConstant.getKeyFromMessage(update.getCallbackQuery().getData());

		BiFunction<Update, ResourceBundle, SendMessage> biFunction = methodFactory
				.get(messageConstant.getKeyFromMessage(update.getCallbackQuery().getData()));

		return biFunction.apply(update, bundle);
	}

	private SendMessage getSendMessage(Update update, String answer) {
		return new SendMessage()
				.setChatId(update.getMessage().getChatId())
				.enableHtml(true)
				.setText(answer);
	}

	private SendMessage getSendMessage(Update update, String answer, ReplyKeyboard replyMarkup) {
		return getSendMessage(update, answer)
				.setReplyMarkup(replyMarkup);
	}

	private SendMessage getSendCallBackMessage(Update update, String answer) {
		return new SendMessage()
				.setChatId(update.getCallbackQuery().getMessage().getChatId())
				.enableHtml(true)
				.setText(answer)
				.setReplyToMessageId(update.getCallbackQuery().getMessage().getMessageId());
	}

	private Map<String, String> getTitlesMessage(String title) {
		String ru = getBundle(LANGUAGE_RU).getString(title);
		String en = getBundle(LANGUAGE_EN).getString(title);
		Map<String, String> titles = new HashMap<>(2);
		titles.put(LANGUAGE_RU, ru);
		titles.put(LANGUAGE_EN, en);
		return titles;
	}

	private Map<String, ReplyKeyboard> getUnSubscribeButtons(ButtonService buttonService, String command) {
		InlineKeyboardMarkup ru = buttonService.getButton(getBundle(LANGUAGE_RU), MessageConstant.BUTTON_UNSUBSCRIBE, command);
		InlineKeyboardMarkup en = buttonService.getButton(getBundle(LANGUAGE_EN), MessageConstant.BUTTON_UNSUBSCRIBE, command);
		Map<String, ReplyKeyboard> buttons = new HashMap<>(2);
		buttons.put(LANGUAGE_RU, ru);
		buttons.put(LANGUAGE_EN, en);
		return buttons;
	}

	private SendMessage generateMessage(TUser tUser, String message, Map<String, String> titles, Map<String, ReplyKeyboard> buttons) {
		return new SendMessage()
				.setChatId(String.valueOf(tUser.getTId()))
				.enableHtml(true)
				.setText(titles.get(tUser.getLanguageCode()) + " " + message)
				.setReplyMarkup(buttons.get(tUser.getLanguageCode()));
	}

	private SendMessage getSubscribe(Update update, ResourceBundle bundle) {
		String answer = bundle.getString(MessageConstant.MESSAGE_SUBSCRIBE_DONE);
		return getSendCallBackMessage(update, answer);
	}

	private SendMessage getUnSubscribe(Update update, ResourceBundle bundle) {
		String answer = bundle.getString(MessageConstant.MESSAGE_UNSUBSCRIBE_DONE);
		return getSendCallBackMessage(update, answer);
	}

	protected abstract Subscribe getSubscribe();

	private SendMessage getSendMessage(Update update, ResourceBundle bundle) {
		TUser tUser = tUserFetFromUser(update.getMessage().getFrom());
		Subscribe subscribe = getSubscribe();
		InlineKeyboardMarkup subscribeButton = getSubscribeButton(bundle, tUser, subscribe);
		String answer = getExchangeRatesMessage(bundle);
		return getSendMessage(update, answer, subscribeButton);
	}

	protected abstract InlineKeyboardMarkup getSubscribeButton(ResourceBundle bundle, TUser tUser, Subscribe subscribe);

	protected abstract String getExchangeRatesMessage(ResourceBundle bundle);
}
