package com.emaster.portal.dal.impl;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
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
import com.emaster.common.dto.CategoryDto;
import com.emaster.common.dto.EmasterException;
import com.emaster.common.dto.PageDto;
import com.emaster.common.exception.PortalException;
import com.emaster.common.utils.HttpQueryUtils;
import com.emaster.portal.dal.CategoryDAL;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CategoryDALImpl implements CategoryDAL {
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
	public PageDto<CategoryDto> findAll(Optional<Integer> page, Optional<Integer> size) throws PortalException {
		Map<String, Object> params = new HashMap<>();
		params.put("page", page.orElse(0));
		params.put("size", size.orElse(Integer.MAX_VALUE));
		URI uri = HttpQueryUtils.buildURI(EmasterURL.DataQuery.CATEGORY.GET_ALL.build(), params);

		ParameterizedTypeReference<PageDto<CategoryDto>> responseType = new ParameterizedTypeReference<PageDto<CategoryDto>>() {
		};

		try {
			ResponseEntity<PageDto<CategoryDto>> response = restTemplate.exchange(uri, HttpMethod.GET, null,
					responseType);

			return response.getBody();
		} catch (HttpClientErrorException e) {
			EmasterException exception = objectMapper.convertValue(e.getResponseBodyAsString(), EmasterException.class);
			throw PortalException.builder()
			.status(exception.getStatus())
			.dateTime(exception.getDateTime())
					.message(exception.getMessage())
					.debugMessage(exception.getDebugMessage()).build();
		}
	}

	@Override
	public CategoryDto create(CategoryDto categoryDto) throws PortalException {
		String url = HttpQueryUtils.buildUrl(EmasterURL.DataQuery.CATEGORY.CREATE.build(), null);
		HttpEntity<CategoryDto> body = new HttpEntity<CategoryDto>(categoryDto);
		try {
			ResponseEntity<CategoryDto> response = restTemplate.exchange(url, HttpMethod.POST, body, CategoryDto.class);
			return response.getBody();
		} catch (HttpClientErrorException e) {
			EmasterException exception = objectMapper.convertValue(e.getResponseBodyAsString(), EmasterException.class);
			throw PortalException.builder().status(exception.getStatus()).dateTime(exception.getDateTime())
					.message(exception.getMessage()).debugMessage(exception.getDebugMessage()).build();
		}
	}

	@Override
	public CategoryDto update(CategoryDto categoryDto) throws PortalException {
		String url = HttpQueryUtils.buildUrl(EmasterURL.DataQuery.CATEGORY.UPDATE.build(), null);
		HttpEntity<CategoryDto> body = new HttpEntity<CategoryDto>(categoryDto);
		try {
			ResponseEntity<CategoryDto> response = restTemplate.exchange(url, HttpMethod.PUT, body, CategoryDto.class);
			return response.getBody();
		} catch (HttpClientErrorException e) {
			EmasterException exception = objectMapper.convertValue(e.getResponseBodyAsString(), EmasterException.class);
			throw PortalException.builder().status(exception.getStatus()).dateTime(exception.getDateTime())
					.message(exception.getMessage()).debugMessage(exception.getDebugMessage()).build();
		}
	}

	@Override
	public CategoryDto findOne(String id) throws PortalException {
		Map<String, Object> params = new HashMap<>();
		params.put(EmasterURL.DataQuery.ID, id);

		URI uri = HttpQueryUtils.buildURI(EmasterURL.DataQuery.CATEGORY.GET_BY_ID.build(), params);
		try {
			ResponseEntity<CategoryDto> response = restTemplate.exchange(uri, HttpMethod.GET, null, CategoryDto.class);
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

		URI uri = HttpQueryUtils.buildURI(EmasterURL.DataQuery.CATEGORY.DELETE.build(), params);
		try {
			restTemplate.exchange(uri, HttpMethod.DELETE, null, CategoryDto.class);
		} catch (HttpClientErrorException e) {
			EmasterException exception = objectMapper.convertValue(e.getResponseBodyAsString(), EmasterException.class);
			throw PortalException.builder().status(exception.getStatus()).dateTime(exception.getDateTime())
					.message(exception.getMessage()).debugMessage(exception.getDebugMessage()).build();
		}
	}

	@Override
	public List<CategoryDto> findForASession(Optional<String> userId) throws PortalException {
		Map<String, Object> params = new HashMap<>();
		params.put("userId", userId.orElse(null));

		URI uri = HttpQueryUtils.buildURI(EmasterURL.DataQuery.CATEGORY.GET_BY_ID.build(), params);
		try {
			ParameterizedTypeReference<List<CategoryDto>> listCategoryDtoType = new ParameterizedTypeReference<List<CategoryDto>>() {
			};
			ResponseEntity<List<CategoryDto>> response = restTemplate.exchange(uri, HttpMethod.POST, null,
					listCategoryDtoType);
			return response.getBody();
		} catch (HttpClientErrorException e) {
			EmasterException exception = objectMapper.convertValue(e.getResponseBodyAsString(), EmasterException.class);
			throw PortalException.builder().status(exception.getStatus()).dateTime(exception.getDateTime())
					.message(exception.getMessage()).debugMessage(exception.getDebugMessage()).build();
		}
	}

}
