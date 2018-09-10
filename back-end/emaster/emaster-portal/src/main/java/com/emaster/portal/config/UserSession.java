package com.emaster.portal.config;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import com.emaster.common.dto.TokenDto;
import com.emaster.common.utils.StringUtils;
import com.emaster.common.utils.TokenUtils;

@RequestScope
@Component
public class UserSession {
	@Autowired
	private HttpServletRequest request;
	private String userId;
	private String email;
	private long expiredTime;

	@PostConstruct
	void init() {
		this.reset();
		String authorizationToken = this.request.getHeader(HttpHeaders.AUTHORIZATION);
		TokenDto tokenDto = TokenUtils.parseToken(authorizationToken);
		this.userId = tokenDto.getUserId();
		this.email = tokenDto.getEmail();
		this.expiredTime = tokenDto.getExpiredTime();
	}
	
	public void reset() {
		this.userId = StringUtils.EMPTY;
		this.email = StringUtils.EMPTY;
		this.expiredTime = 0;
	}
	
	public String getAuthor() {
		return email;
	}
	
	public long getExpiredTime() {
		return expiredTime;
	}
	
	public String getUserId() {
		return userId;
	}

}