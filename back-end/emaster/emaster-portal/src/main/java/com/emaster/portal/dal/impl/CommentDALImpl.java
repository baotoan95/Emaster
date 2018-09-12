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
import com.emaster.common.dto.CommentDto;
import com.emaster.common.dto.PageDto;
import com.emaster.common.exception.EmasterException;
import com.emaster.common.exception.PortalException;
import com.emaster.common.utils.HttpQueryUtils;
import com.emaster.portal.dal.CommentDAL;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CommentDALImpl implements CommentDAL {
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
	public PageDto<CommentDto> findAll(Optional<Integer> page, Optional<Integer> size) throws PortalException {
		Map<String, Object> params = new HashMap<>();
		params.put("page", page.orElse(null));
		params.put("size", size.orElse(Integer.MAX_VALUE));
		URI uri = HttpQueryUtils.buildURI(EmasterURL.DataQuery.COMMENT.GET_ALL.build(), params);
		ParameterizedTypeReference<PageDto<CommentDto>> responseType = new ParameterizedTypeReference<PageDto<CommentDto>>() {
		};

		try {
			ResponseEntity<PageDto<CommentDto>> response = restTemplate.exchange(uri, HttpMethod.GET, null,
					responseType);
			return response.getBody();
		} catch (HttpClientErrorException e) {
			EmasterException exception = objectMapper.convertValue(e.getResponseBodyAsString(), EmasterException.class);
			throw PortalException.builder().status(exception.getStatus()).dateTime(exception.getDateTime())
					.message(exception.getMessage()).debugMessage(exception.getDebugMessage()).build();
		}

	}

	@Override
	public CommentDto create(CommentDto commentDto) throws PortalException {
		String url = HttpQueryUtils.buildUrl(EmasterURL.DataQuery.COMMENT.CREATE.build(),null);
		HttpEntity<CommentDto> body = new HttpEntity<CommentDto>(commentDto);
		try {
			ResponseEntity<CommentDto> response = restTemplate.exchange(url, HttpMethod.POST, body, CommentDto.class);
			return response.getBody();
		} catch (HttpClientErrorException e) {
			EmasterException exception = objectMapper.convertValue(e.getResponseBodyAsString(), EmasterException.class);
			throw PortalException.builder().status(exception.getStatus()).dateTime(exception.getDateTime())
					.message(exception.getMessage()).debugMessage(exception.getDebugMessage()).build();
		}

	}

	@Override
	public CommentDto update(CommentDto commentDto) throws PortalException {
		String url = HttpQueryUtils.buildUrl(EmasterURL.DataQuery.COMMENT.UPDATE.build(), null);
		HttpEntity<CommentDto> body = new HttpEntity<CommentDto>(commentDto);
		try {
			ResponseEntity<CommentDto> response = restTemplate.exchange(url, HttpMethod.PUT, body, CommentDto.class);
			return response.getBody();
		} catch (HttpClientErrorException e) {
			EmasterException exception = objectMapper.convertValue(e.getResponseBodyAsString(), EmasterException.class);
			throw PortalException.builder().status(exception.getStatus()).dateTime(exception.getDateTime())
					.message(exception.getMessage()).debugMessage(exception.getDebugMessage()).build();
		}

	}

	@Override
	public CommentDto findOne(String id) throws PortalException {
		Map<String, Object> params = new HashMap<>();
		params.put(EmasterURL.DataQuery.ID, id);

		URI uri = HttpQueryUtils.buildURI(EmasterURL.DataQuery.COMMENT.GET_BY_ID.build(), params);
		try {
			ResponseEntity<CommentDto> response = restTemplate.exchange(uri, HttpMethod.GET, null, CommentDto.class);
			return response.getBody();
		} catch (HttpClientErrorException e) {
			EmasterException exception = objectMapper.convertValue(e.getResponseBodyAsString(), EmasterException.class);
			throw PortalException.builder().status(exception.getStatus()).dateTime(exception.getDateTime())
					.message(exception.getMessage()).debugMessage(exception.getDebugMessage()).build();
		}

	}

	@Override
	public void delete(String id) throws PortalException {
		Map<String, Object> params = new HashMap<>();
		params.put(EmasterURL.DataQuery.ID, id);

		URI uri = HttpQueryUtils.buildURI(EmasterURL.DataQuery.COMMENT.DELETE.build(), params);
		try {
			restTemplate.exchange(uri, HttpMethod.DELETE, null, CommentDto.class);
		} catch (HttpClientErrorException e) {
			EmasterException exception = objectMapper.convertValue(e.getResponseBodyAsString(), EmasterException.class);
			throw PortalException.builder().status(exception.getStatus()).dateTime(exception.getDateTime())
					.message(exception.getMessage()).debugMessage(exception.getDebugMessage()).build();
		}

	}

}
