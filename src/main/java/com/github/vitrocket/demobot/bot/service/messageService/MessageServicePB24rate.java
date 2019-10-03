package com.github.vitrocket.demobot.bot.service.messageService;

import com.github.vitrocket.demobot.bot.service.MessageConstant;
import com.github.vitrocket.demobot.bot.service.button.ButtonService;
import com.github.vitrocket.demobot.bot.service.textService.TextMessageContext;
import com.github.vitrocket.demobot.bot.service.textService.impl.RatePB24;
import com.github.vitrocket.demobot.domain.ScribeAction;
import com.github.vitrocket.demobot.domain.Subscribe;
import com.github.vitrocket.demobot.domain.TUser;
import com.github.vitrocket.demobot.service.SubscribeService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.Objects;
import java.util.ResourceBundle;

import static com.github.vitrocket.demobot.bot.service.MessageConstant.*;

@Service
public class MessageServicePB24rate extends MessageServiceAbstract implements MessageService {

	private final RatePB24 ratePB24;

	public MessageServicePB24rate(TextMessageContext textMessageContext, ButtonService buttonService, SubscribeService subscribeService, RatePB24 ratePB24, MessageConstant messageConstant) {
		super(textMessageContext, buttonService, messageConstant, subscribeService);
		this.ratePB24 = ratePB24;
	}

	protected Subscribe getSubscribe() {
		return subscribeService.getByScribeAction(ScribeAction.ratePB24);
	}

	protected InlineKeyboardMarkup getSubscribeButton(ResourceBundle bundle, TUser tUser, Subscribe subscribe) {
		InlineKeyboardMarkup subscribeButton = null;
		if (!subscribe.getTUsers().contains(tUser)) {
			subscribeButton = buttonService.getButton(bundle, BUTTON_SUBSCRIBE, BUTTON_EXCHANGE_SUBSCRIBE_COMMAND);
		}
		return subscribeButton;
	}

	protected String getExchangeRatesMessage(ResourceBundle bundle) {
		String rates = ratePB24.getRates();
		if (Objects.isNull(rates)) {
			return bundle.getString(MESSAGE_EXCHANGE_RATES_TITLE) + " " + "Sorry, Error.";
		}
		return bundle.getString(MESSAGE_EXCHANGE_RATES_TITLE) + " " + rates;
	}
}
