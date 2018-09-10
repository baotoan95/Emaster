package com.emaster.portal.dal.impl;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.emaster.common.constant.EmasterURL;
import com.emaster.common.dto.CreateUserMemoryDto;
import com.emaster.common.dto.EmasterException;
import com.emaster.common.dto.UserMemoryDto;
import com.emaster.common.exception.PortalException;
import com.emaster.common.utils.HttpQueryUtils;
import com.emaster.portal.dal.UserMemoryDAL;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class UserMemoryDALImpl implements UserMemoryDAL {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public List<UserMemoryDto> findMissing(String userId, String categoryId, int pointLimit) throws PortalException {
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId);
		params.put("categoryId", categoryId);
		params.put("pointLimit", pointLimit);
		URI uri = HttpQueryUtils.buildURI(EmasterURL.DataQuery.USER_MEMORY.GET_MISSING_STATEMENTS.build(), params);

		ParameterizedTypeReference<List<UserMemoryDto>> responseType = new ParameterizedTypeReference<List<UserMemoryDto>>() {
		};

		try {
			ResponseEntity<List<UserMemoryDto>> response = restTemplate.exchange(uri, HttpMethod.GET, null, responseType);

			return response.getBody();
		} catch (HttpClientErrorException e) {
			EmasterException exception = objectMapper.convertValue(e.getResponseBodyAsString(), EmasterException.class);
			throw PortalException.builder().status(exception.getStatus()).dateTime(exception.getDateTime())
					.message(exception.getMessage()).debugMessage(exception.getDebugMessage()).build();
		}
	}

	@Override
	public UserMemoryDto addToMemory(String userId, String statementId) throws PortalException {
		String url = HttpQueryUtils.buildUrl(EmasterURL.DataQuery.USER_MEMORY.ADD_TO_MEMORY.build(), null);
		HttpEntity<CreateUserMemoryDto> body = new HttpEntity<>(CreateUserMemoryDto.builder().userId(userId).statementId(statementId).build());
		ParameterizedTypeReference<UserMemoryDto> responseType = new ParameterizedTypeReference<UserMemoryDto>() {
		};

		try {
			ResponseEntity<UserMemoryDto> response = restTemplate.exchange(url, HttpMethod.POST, body, responseType);

			return response.getBody();
		} catch (HttpClientErrorException e) {
			EmasterException exception = objectMapper.convertValue(e.getResponseBodyAsString(), EmasterException.class);
			throw PortalException.builder().status(exception.getStatus()).dateTime(exception.getDateTime())
					.message(exception.getMessage()).debugMessage(exception.getDebugMessage()).build();
		}
	}

}
