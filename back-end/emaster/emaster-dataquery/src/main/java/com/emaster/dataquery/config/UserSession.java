package com.emaster.dataquery.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
public class UserSession {
	@Autowired
	private HttpRequest httpRequest;
	
	public UserSession() {
		System.out.println("Auth: " + httpRequest.getHeaders().get("Authorization"));
	}
}
