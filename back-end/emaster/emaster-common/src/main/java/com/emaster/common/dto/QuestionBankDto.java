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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionBankDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private QuestionType type;

	private String prompt;

	private List<String> promptPieces;

	private List<String> correctSolutions;

	private List<String> compatTranslations;

	private List<String> imageUrl;

	private String sourceLang;

	private String targetLang;

	private String soundUrl;

	private String slowSound;

	private List<String> choises;

	private int discussCount;

	private CategoryDto category;

	private List<CommentDto> comments;
}
