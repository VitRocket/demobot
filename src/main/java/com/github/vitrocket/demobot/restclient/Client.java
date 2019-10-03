package com.github.vitrocket.demobot.restclient;

import com.github.vitrocket.demobot.restclient.exception.RestClientException;

import java.net.URI;
import java.net.URISyntaxException;

public interface Client {

	default URI getUri(String url) throws RestClientException {
		URI uri;
		try {
			uri = new URI(url);
		} catch (URISyntaxException e) {
			throw new RestClientException("Ошибка определения адреса: " + e.getMessage());
		}
		return uri;
	}
}
