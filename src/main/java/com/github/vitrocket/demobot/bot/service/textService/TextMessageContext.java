package com.github.vitrocket.demobot.bot.service.textService;

import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class TextMessageContext {

	@Setter
	private TextMessageService textMessageService;

	public String getTextMessage() {
		return textMessageService.getTextMessage();
	}
}
