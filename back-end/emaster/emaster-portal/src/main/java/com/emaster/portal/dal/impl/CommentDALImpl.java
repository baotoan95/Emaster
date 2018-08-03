package com.emaster.portal.dal.impl;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.emaster.common.dto.CommentDto;
import com.emaster.common.dto.EmasterException;
import com.emaster.common.dto.PageDto;
import com.emaster.common.exception.PortalException;
import com.emaster.common.utils.HttpQueryUtils;
import com.emaster.portal.dal.CommentDAL;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CommentDALImpl implements CommentDAL {
	@Value("${dataquery.baseUrl}")
	private String dataQueryBaseUrl;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ObjectMapper objectMapper;

	private String endPointPrefix = "/comments";

	private final String ID_PARAM = "commentId";

	@Override
	public PageDto<CommentDto> findAll(Optional<Integer> page, Optional<Integer> size) throws PortalException {
		Map<String, Object> params = new HashMap<>();
		params.put("page", page.orElse(null));
		params.put("size", size.orElse(null));
		String uri = HttpQueryUtils.buildUrl(dataQueryBaseUrl + endPointPrefix, params);
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
		String url = HttpQueryUtils.buildUrl(dataQueryBaseUrl + endPointPrefix, null);
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
		String url = HttpQueryUtils.buildUrl(dataQueryBaseUrl + endPointPrefix, null);
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
		params.put(ID_PARAM, id);

		URI uri = HttpQueryUtils.buildURI(dataQueryBaseUrl + endPointPrefix + "/{" + ID_PARAM + "}", params);
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
		params.put(ID_PARAM, id);

		URI uri = HttpQueryUtils.buildURI(dataQueryBaseUrl + endPointPrefix + "/{" + ID_PARAM + "}", params);
		try {
			restTemplate.exchange(uri, HttpMethod.DELETE, null, CommentDto.class);

		} catch (HttpClientErrorException e) {
			EmasterException exception = objectMapper.convertValue(e.getResponseBodyAsString(), EmasterException.class);
			throw PortalException.builder().status(exception.getStatus()).dateTime(exception.getDateTime())
					.message(exception.getMessage()).debugMessage(exception.getDebugMessage()).build();
		}

	}

}
