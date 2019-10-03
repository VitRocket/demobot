package com.github.vitrocket.demobot.service;

import com.github.vitrocket.demobot.domain.ScribeAction;
import com.github.vitrocket.demobot.domain.Subscribe;

public interface SubscribeService {

	void save(Subscribe subscribe);

	void update(Subscribe subscribe);

	Subscribe findByScribeAction(ScribeAction scribeAction);

	Subscribe getByScribeAction(ScribeAction scribeAction);

}
