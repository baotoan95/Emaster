package com.emaster.portal.dal.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.emaster.common.dto.PageDto;
import com.emaster.common.dto.UserDto;
import com.emaster.portal.dal.UserDAL;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class UserDALImpl implements UserDAL {
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public PageDto<UserDto> getUsers(Optional<Integer> page, Optional<Integer> size) {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new FormHttpMessageConverter());
		messageConverters.add(new StringHttpMessageConverter());
		messageConverters.add(new MappingJackson2HttpMessageConverter());
		restTemplate.setMessageConverters(messageConverters);
		
		ParameterizedTypeReference<PageDto<UserDto>> responseType = new ParameterizedTypeReference<PageDto<UserDto>>() {};
		ResponseEntity<String> response = restTemplate.exchange("http://localhost:8085/users", HttpMethod.GET, null, String.class);
		if(response.hasBody()) {
			ObjectMapper objectMapper = new ObjectMapper();
			PageDto<UserDto> rs = objectMapper.convertValue(response.getBody(), PageDto.class);
			return rs;
		}
		return null;
	}

}
