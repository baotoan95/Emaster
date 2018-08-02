package com.emaster.common.dto;

import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import com.emaster.common.enums.Gender;
import com.emaster.common.enums.Language;
import com.emaster.common.enums.UserRole;
import com.emaster.common.utils.JsonMapperUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String email;

	private String password;

	private String displayName;

	private String socialNetwork;

	private Gender gender;

	private String bio;

	private String currentLevel;

	private String avatar;

	private String targetDaily;

	private int remindTime;

	private String location;

	private Language nativeLang;

	private Language learningLang;

	private long curentPoint;

	private Date createDate;

	private UserRole role;

	@JsonCreator
	public static UserDto Create(String jsonString) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = JsonMapperUtils.getObjectMapper();
		return mapper.readValue(jsonString, UserDto.class);
	}
}
