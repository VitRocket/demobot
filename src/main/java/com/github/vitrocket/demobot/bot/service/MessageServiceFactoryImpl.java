package com.github.vitrocket.demobot.bot.service;

import com.github.vitrocket.demobot.bot.service.messageService.MessageService;
import com.github.vitrocket.demobot.bot.service.messageService.MessageServiceAbout;
import com.github.vitrocket.demobot.bot.service.messageService.MessageServicePB24rate;
import com.github.vitrocket.demobot.bot.service.messageService.MessageServiceStart;
import com.github.vitrocket.demobot.domain.ScribeAction;
import com.github.vitrocket.demobot.util.ContextProvider;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Supplier;

import static com.github.vitrocket.demobot.bot.service.MessageConstant.*;

@Service
public class MessageServiceFactoryImpl implements MessageServiceFactory {

	private static Map<String, Supplier<MessageService>> supplierMap = new HashMap<>();

	@PostConstruct
	public void post() {
		ResourceBundle en = ResourceBundle.getBundle(MESSAGES, MessageService.LOCALE_ENGLISH);
		ResourceBundle ru = ResourceBundle.getBundle(MESSAGES, MessageService.LOCALE_RUSSIAN);
		supplierMap.put("/start", this::getMessageServiceStart);
		supplierMap.put(en.getString(MAIN_MENU_START), this::getMessageServiceStart);
		supplierMap.put(ru.getString(MAIN_MENU_START), this::getMessageServiceStart);
		supplierMap.put(en.getString(MAIN_MENU_ABOUT), this::getMessageServiceAbout);
		supplierMap.put(ru.getString(MAIN_MENU_ABOUT), this::getMessageServiceAbout);
		supplierMap.put(en.getString(MAIN_MENU_EXCHANGE_RATES), this::getMessageServiceCYRates);
		supplierMap.put(ru.getString(MAIN_MENU_EXCHANGE_RATES), this::getMessageServiceCYRates);
		supplierMap.put(en.getString(BUTTON_EXCHANGE_SUBSCRIBE_COMMAND), this::getMessageServiceCYRates);
		supplierMap.put(ru.getString(BUTTON_EXCHANGE_SUBSCRIBE_COMMAND), this::getMessageServiceCYRates);
		supplierMap.put(en.getString(BUTTON_EXCHANGE_UNSUBSCRIBE_COMMAND), this::getMessageServiceCYRates);
		supplierMap.put(ru.getString(BUTTON_EXCHANGE_UNSUBSCRIBE_COMMAND), this::getMessageServiceCYRates);
		supplierMap.put(ScribeAction.ratePB24.name(), this::getMessageServiceCYRates);
	}

	@Override
	public MessageService getMessageService(String type) {
		Supplier<MessageService> supplier = supplierMap.get(type);
		if (supplier != null) {
			return supplier.get();
		} else {
			return getMessageServiceStart();
		}
	}

	private MessageServiceStart getMessageServiceStart() {
		return ContextProvider.getBean(MessageServiceStart.class);
	}

	private MessageServiceAbout getMessageServiceAbout() {
		return ContextProvider.getBean(MessageServiceAbout.class);
	}

	private MessageServicePB24rate getMessageServiceCYRates() {
		return ContextProvider.getBean(MessageServicePB24rate.class);
	}
}
