package com.github.vitrocket.demobot.service;

import com.github.vitrocket.demobot.domain.PeriodicityType;
import com.github.vitrocket.demobot.domain.ScribeAction;
import com.github.vitrocket.demobot.domain.ScribeType;
import com.github.vitrocket.demobot.domain.Subscribe;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscribeServiceImpl implements SubscribeService {

	@Override
	public void save(Subscribe subscribe) {

	}

	@Override
	public void update(Subscribe subscribe) {

	}

	@Override
	public Subscribe findByScribeAction(ScribeAction scribeAction) {
		return getDefaultSubscribe(scribeAction);
	}

	@Override
	public Subscribe getByScribeAction(ScribeAction action) {
		Subscribe subscribe = findByScribeAction(action);
		if (Objects.isNull(subscribe)) {
			subscribe = getDefaultSubscribe(action);
			save(subscribe);
		}
		return subscribe;
	}

	private Subscribe getDefaultSubscribe(ScribeAction action) {
		Subscribe subscribe = new Subscribe();
		subscribe.setScribeAction(action);
		subscribe.setPeriodicityType(PeriodicityType.everyDay);
		subscribe.setEnable(true);
		subscribe.setScribeType(ScribeType.userMessage);
		subscribe.setTime(LocalTime.of(10, 00));
		return subscribe;
	}
}
