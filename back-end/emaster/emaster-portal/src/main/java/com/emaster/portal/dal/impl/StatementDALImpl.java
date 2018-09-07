package com.emaster.portal.dal.impl;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.emaster.common.constant.EmasterURL;
import com.emaster.common.dto.EmasterException;
import com.emaster.common.dto.PageDto;
import com.emaster.common.dto.StatementDto;
import com.emaster.common.exception.PortalException;
import com.emaster.common.utils.HttpQueryUtils;
import com.emaster.portal.dal.StatementDAL;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class StatementDALImpl implements StatementDAL {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public StatementDto create(StatementDto statementDto) throws PortalException {
		HttpEntity<StatementDto> body = new HttpEntity<>(statementDto);
		URI uri = HttpQueryUtils.buildURI(EmasterURL.DataQuery.STATEMENT.CREATE.build(), null);
		try {
			ResponseEntity<StatementDto> response = restTemplate.exchange(uri, HttpMethod.POST, body, StatementDto.class);
			return response.getBody();
		} catch (HttpClientErrorException e) {
			EmasterException exception = objectMapper.convertValue(e.getResponseBodyAsString(), EmasterException.class);
			throw PortalException.builder()
			.status(exception.getStatus())
			.dateTime(exception.getDateTime())
			.debugMessage(exception.getDebugMessage())
			.message(exception.getMessage()).build();
		}
	}

	@Override
	public PageDto<StatementDto> findAll(Optional<Integer> page, Optional<Integer> size) throws PortalException {
		Map<String, Object> params = new HashMap<>();
		params.put("page", page.orElse(0));
		params.put("size", size.orElse(Integer.MAX_VALUE));
		URI uri = HttpQueryUtils.buildURI(EmasterURL.DataQuery.STATEMENT.GET_ALL.build(), params);
		ParameterizedTypeReference<PageDto<StatementDto>> responseType = new ParameterizedTypeReference<PageDto<StatementDto>>() {
		};
		try {
			ResponseEntity<PageDto<StatementDto>> response = restTemplate.exchange(uri, HttpMethod.GET, null, responseType);
			return response.getBody();
		} catch (HttpClientErrorException e) {
			EmasterException exception = objectMapper.convertValue(e.getResponseBodyAsString(), EmasterException.class);
			throw PortalException.builder()
			.status(exception.getStatus())
			.dateTime(exception.getDateTime())
			.debugMessage(exception.getDebugMessage())
			.message(exception.getMessage()).build();
		}
	}

	@Override
	public StatementDto update(StatementDto statementDto) throws PortalException {
		HttpEntity<StatementDto> body = new HttpEntity<>(statementDto);
		URI uri = HttpQueryUtils.buildURI(EmasterURL.DataQuery.STATEMENT.UPDATE.build(), null);
		try {
			ResponseEntity<StatementDto> response = restTemplate.exchange(uri, HttpMethod.PUT, body, StatementDto.class);
			return response.getBody();
		} catch (HttpClientErrorException e) {
			EmasterException exception = objectMapper.convertValue(e.getResponseBodyAsString(), EmasterException.class);
			throw PortalException.builder()
			.status(exception.getStatus())
			.dateTime(exception.getDateTime())
			.debugMessage(exception.getDebugMessage())
			.message(exception.getMessage()).build();
		}
	}

	@Override
	public StatementDto findOne(String id) throws PortalException {
		Map<String, Object> params = new HashMap<>();
		params.put(EmasterURL.DataQuery.ID, id);
		URI uri = HttpQueryUtils.buildURI(EmasterURL.DataQuery.STATEMENT.GET_BY_ID.build(), params);
		try {
			ResponseEntity<StatementDto> response = restTemplate.exchange(uri, HttpMethod.GET, null, StatementDto.class);
			return response.getBody();
		} catch (HttpClientErrorException e) {
			EmasterException exception = objectMapper.convertValue(e.getResponseBodyAsString(), EmasterException.class);
			throw PortalException.builder()
			.status(exception.getStatus())
			.dateTime(exception.getDateTime())
			.debugMessage(exception.getDebugMessage())
			.message(exception.getMessage()).build();
		}
	}

	@Override
	public void delete(String id) throws PortalException {
		Map<String, Object> params = new HashMap<>();
		params.put(EmasterURL.DataQuery.ID, id);
		URI uri = HttpQueryUtils.buildURI(EmasterURL.DataQuery.STATEMENT.DELETE.build(), params);
		try {
			restTemplate.exchange(uri, HttpMethod.DELETE, null, StatementDto.class);
		} catch (HttpClientErrorException e) {
			EmasterException exception = objectMapper.convertValue(e.getResponseBodyAsString(), EmasterException.class);
			throw PortalException.builder()
			.status(exception.getStatus())
			.dateTime(exception.getDateTime())
			.debugMessage(exception.getDebugMessage())
			.message(exception.getMessage()).build();
		}
	}

	@Override
	public PageDto<StatementDto> findByCategory(String categoryId, Optional<Integer> page, Optional<Integer> size)
			throws PortalException {
		Map<String, Object> params = new HashMap<>();
		params.put("page", page.orElse(0));
		params.put("size", size.orElse(Integer.MAX_VALUE));
		params.put("categoryId", categoryId);
		URI uri = HttpQueryUtils.buildURI(EmasterURL.DataQuery.STATEMENT.GET_BY_CATEGORY_ID.build(), params);
		ParameterizedTypeReference<PageDto<StatementDto>> responseType = new ParameterizedTypeReference<PageDto<StatementDto>>() {
		};
		try {
			ResponseEntity<PageDto<StatementDto>> response = restTemplate.exchange(uri, HttpMethod.GET, null, responseType);
			return response.getBody();
		} catch (HttpClientErrorException e) {
			EmasterException exception = objectMapper.convertValue(e.getResponseBodyAsString(), EmasterException.class);
			throw PortalException.builder()
			.status(exception.getStatus())
			.dateTime(exception.getDateTime())
			.debugMessage(exception.getDebugMessage())
			.message(exception.getMessage()).build();
		}
	}

}
