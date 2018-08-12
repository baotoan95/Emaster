package org.emaster.authorization.dal.impl;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.emaster.authorization.dal.UserDAL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.emaster.common.constant.EmasterURL;
import com.emaster.common.dto.EmasterException;
import com.emaster.common.dto.UserDto;
import com.emaster.common.exception.PortalException;
import com.emaster.common.utils.HttpQueryUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class UserDALImpl implements UserDAL {
	private RestTemplate restTemplate;
	private ObjectMapper objectMapper;

	@Autowired
	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}
	
	@Autowired
	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	@Override
	public UserDto findByUserEmail(String email) throws PortalException {
		Map<String, Object> params = new HashMap<>();
		params.put(EmasterURL.DataQuery.EMAIL, email);
		URI uri = HttpQueryUtils.buildURI(EmasterURL.DataQuery.USER.GET_BY_EMAIL.build(), params);
		try {
			ResponseEntity<UserDto> response = restTemplate.exchange(uri, HttpMethod.GET, null, UserDto.class);
			return response.getBody();
		} catch (HttpClientErrorException e) {
			EmasterException exception = objectMapper.convertValue(e.getResponseBodyAsString(), EmasterException.class);
			throw PortalException.builder().status(exception.getStatus()).dateTime(exception.getDateTime())
					.message(exception.getMessage()).debugMessage(exception.getDebugMessage()).build();
		}
	}

}
