package com.github.vitrocket.demobot.logger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

@Slf4j
@Aspect
@Component
public class LogAspect {

	/**
	 * Логирует все приходящие сообщения
	 *
	 * @param joinPoint point
	 */
	@Before("execution(* com.github.vitrocket.demobot.bot.BotConveyor.botConveyor(*))")
	public void beforeBotConveyor(JoinPoint joinPoint) {
		final Update update = (Update) joinPoint.getArgs()[0];
		try {
			ObjectMapper mapper = new ObjectMapper();
			log.info("Update: {}", mapper.writeValueAsString(update));
		} catch (JsonProcessingException e) {
			log.warn("Update: {}", update.toString());
			log.error("Update: {}, doesn't parse: {}", update.getUpdateId(), e.getMessage());
		}
	}

	/**
	 * Логирует все отправляемые сообщения
	 *
	 * @param joinPoint point
	 */
	@Before("execution(* com.github.vitrocket.demobot.bot.Sender.sendMessage(*))")
	public void beforeSendMessage(JoinPoint joinPoint) {
		final SendMessage sendMessage = (SendMessage) joinPoint.getArgs()[0];
		try {
			ObjectMapper mapper = new ObjectMapper();
			log.info("SendMessage: {}", mapper.writeValueAsString(sendMessage));
		} catch (JsonProcessingException e) {
			log.warn("SendMessage: {}", sendMessage.toString());
			log.error("SendMessage: {}, doesn't parse: {}", sendMessage.getChatId(), e.getMessage());
		}
	}

	/**
	 * Логирует результат отправляемого сообщения
	 *
	 * @param result сообщение телеграмма
	 */
	@AfterReturning(value = "execution(* com.github.vitrocket.demobot.bot.Sender.sendMessage(*))", returning = "result")
	public void afterReturningSendMessage(Object result) {
		final Message sendMessage = (Message) result;
		try {
			ObjectMapper mapper = new ObjectMapper();
			log.info("Message: {}", mapper.writeValueAsString(sendMessage));
		} catch (JsonProcessingException e) {
			log.warn("Message: {}", sendMessage.toString());
			log.error("Message: {}, doesn't parse: {}", sendMessage.getChatId(), e.getMessage());
		}
	}
}
