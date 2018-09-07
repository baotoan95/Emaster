package com.emaster.common.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.emaster.common.enums.Language;
import com.emaster.common.enums.StatementType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
@ToString
public class StatementDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private StatementType type;
	private String content;
	private List<StatementDto> correctAnswers;
	private List<StatementDto> incorrectAnswers;
	private String explaination;
	private String sound;
	private String slowSound;
	private String image;
	private Language language;
	private String bestAnswer;
	private Date createdDate;
	@JsonProperty("createdBy")
	private String createdByEmail;
	private String categoryId;
	private Date updatedDate;
}