package com.emaster.portal.dal.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.emaster.common.dto.EmasterException;
import com.emaster.common.dto.PageDto;
import com.emaster.common.dto.UserDto;
import com.emaster.common.exception.PortalException;
import com.emaster.common.utils.HttpQueryUtils;
import com.emaster.portal.dal.UserDAL;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class UserDALImpl implements UserDAL {
	@Value("${dataquery.baseUrl}")
	private String dataQueryBaseUrl;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public PageDto<UserDto> getUsers(Optional<Integer> page, Optional<Integer> size) throws PortalException {
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(new FormHttpMessageConverter());
		messageConverters.add(new StringHttpMessageConverter());
		messageConverters.add(new MappingJackson2HttpMessageConverter());
		restTemplate.setMessageConverters(messageConverters);

		Map<String, Object> params = new HashMap<>();
		params.put("page", page.orElse(null));
		params.put("size", size.orElse(null));
		String uri = HttpQueryUtils.buildUrl("http://localhost:8085/users", params);

		ParameterizedTypeReference<PageDto<UserDto>> responseType = new ParameterizedTypeReference<PageDto<UserDto>>() {
		};

		try {
			ResponseEntity<PageDto<UserDto>> response = restTemplate.exchange(uri, HttpMethod.GET, null, responseType);

			return response.getBody();
		} catch (HttpClientErrorException e) {
			EmasterException exception = objectMapper.convertValue(e.getResponseBodyAsString(),
					EmasterException.class);
			throw PortalException.builder()
				.status(exception.getStatus())
				.dateTime(exception.getDateTime())
				.message(exception.getMessage())
				.debugMessage(exception.getDebugMessage()).build();
		}
	}

}
