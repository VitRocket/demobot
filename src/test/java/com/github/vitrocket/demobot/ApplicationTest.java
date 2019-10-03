package com.github.vitrocket.demobot;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.telegram.telegrambots.starter.TelegramBotStarterConfiguration;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@ActiveProfiles("test")
@SpringBootTest
@ContextConfiguration(initializers = DemoBotApplicationContextInitializer.class)
@EnableAutoConfiguration(exclude = TelegramBotStarterConfiguration.class)
@interface ApplicationTest {
}
