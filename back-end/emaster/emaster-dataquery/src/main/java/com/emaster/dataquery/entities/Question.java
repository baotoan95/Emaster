package com.emaster.dataquery.entities;

import java.io.Serializable;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.emaster.dataquery.enums.QuestionType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "questions")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private ObjectId id;

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

	private Category category;

	private List<Comment> comments;
}
