package com.emaster.portal.dal.impl;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
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

import com.emaster.common.dto.CategoryDto;
import com.emaster.common.dto.EmasterException;
import com.emaster.common.dto.PageDto;
import com.emaster.common.exception.PortalException;
import com.emaster.common.utils.HttpQueryUtils;
import com.emaster.portal.dal.CategoryDAL;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CategoryDALImpl implements CategoryDAL {
	@Value("${dataquery.baseUrl}")
	private String dataQueryBaseUrl;
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private ObjectMapper objectMapper;
	private String endPointPrefix = "/categories";

	private final String ID_PARAM = "categoryId";
	private final String USER_ID_PARAM = "userId";

	@Override
	public PageDto<CategoryDto> findAll(Optional<Integer> page, Optional<Integer> size) throws PortalException {
		Map<String, Object> params = new HashMap<>();
		params.put("page", page.orElse(null));
		params.put("size", size.orElse(null));
		String uri = HttpQueryUtils.buildUrl(dataQueryBaseUrl + endPointPrefix, params);

		ParameterizedTypeReference<PageDto<CategoryDto>> responseType = new ParameterizedTypeReference<PageDto<CategoryDto>>() {
		};

		try {
			ResponseEntity<PageDto<CategoryDto>> response = restTemplate.exchange(uri, HttpMethod.GET, null,
					responseType);

			return response.getBody();
		} catch (HttpClientErrorException e) {
			EmasterException exception = objectMapper.convertValue(e.getResponseBodyAsString(), EmasterException.class);
			throw PortalException.builder().status(exception.getStatus()).dateTime(exception.getDateTime())
					.message(exception.getMessage()).debugMessage(exception.getDebugMessage()).build();
		}
	}

	@Override
	public CategoryDto create(CategoryDto category) throws PortalException {
		String url = HttpQueryUtils.buildUrl(dataQueryBaseUrl + endPointPrefix, null);
		HttpEntity<CategoryDto> body = new HttpEntity<CategoryDto>(category);
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
	public CategoryDto update(CategoryDto category) throws PortalException {
		String url = HttpQueryUtils.buildUrl(dataQueryBaseUrl + endPointPrefix, null);
		HttpEntity<CategoryDto> body = new HttpEntity<CategoryDto>(category);
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
	public CategoryDto findOne(String id) throws PortalException {
		Map<String, Object> params = new HashMap<>();
		params.put(ID_PARAM, id);

		URI uri = HttpQueryUtils.buildURI(dataQueryBaseUrl + endPointPrefix + "/{" + ID_PARAM + "}", params);
		try {
			ResponseEntity<CategoryDto> response = restTemplate.exchange(uri, HttpMethod.POST, null, CategoryDto.class);
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
			restTemplate.exchange(uri, HttpMethod.POST, null, CategoryDto.class);
		} catch (HttpClientErrorException e) {
			EmasterException exception = objectMapper.convertValue(e.getResponseBodyAsString(), EmasterException.class);
			throw PortalException.builder().status(exception.getStatus()).dateTime(exception.getDateTime())
					.message(exception.getMessage()).debugMessage(exception.getDebugMessage()).build();
		}
	}

	@Override
	public List<CategoryDto> findForASession(Optional<String> userId) throws PortalException {
		Map<String, Object> params = new HashMap<>();
		params.put(USER_ID_PARAM, userId.orElse(null));

		URI uri = HttpQueryUtils.buildURI(dataQueryBaseUrl + endPointPrefix + "/{" + USER_ID_PARAM + "}", params);
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
