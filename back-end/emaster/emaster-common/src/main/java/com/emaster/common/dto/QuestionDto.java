package com.emaster.common.dto;

import java.io.Serializable;
import java.util.List;

import com.emaster.common.enums.QuestionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2288643637881726406L;
	private String id;
	private String content;
	private QuestionType type;
	private List<AnswerDto> answers;
	private String sound;
	private String slowSound;
	private String image;
	private String bestAnswer;
}
