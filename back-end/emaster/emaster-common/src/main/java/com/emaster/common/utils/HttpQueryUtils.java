package com.emaster.common.utils;

import java.net.URI;
import java.util.Map;
import java.util.Objects;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpQueryUtils {
	public static String buildUrl(String url, Map<String, Object> params) {
		log.info("Build URL: {}", url);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		if(Objects.nonNull(params)) {
			for(Map.Entry<String, Object> param: params.entrySet()) {
				if(param.getValue() != null) {
					builder.queryParam(param.getKey(), param.getValue());
				}
			}
		}
		log.info("Build URL finished {}", builder.toUriString());
		return builder.toUriString();
	}
	
	public static URI buildURI(String uri, Map<String, Object> params) {
		log.info("Build URI: {}", uri);
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(uri);
		if(Objects.nonNull(params)) {
			log.info("Buil URL finished {}", builder.buildAndExpand(params).toUriString());
			return builder.buildAndExpand(params).toUri();
		} else {
			log.info("Buil URL finished {}", builder.toUriString());
			return builder.build().toUri();
		}
	}
}
