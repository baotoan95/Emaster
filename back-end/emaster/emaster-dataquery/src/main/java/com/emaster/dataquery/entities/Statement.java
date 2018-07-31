package com.emaster.dataquery.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.emaster.common.enums.Language;
import com.emaster.common.enums.StatementType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "statements")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Statement implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	private StatementType type;
	private String content;
	@DBRef
	private List<Statement> correctAnswers;
	@DBRef
	private List<Statement> incorrectAnswers;
	private String explaination;
	private String sound;
	private String slowSound;
	private String image;
	private Language language;
	private String bestAnswer;
	private Date createdDate;
	private Date updatedDate;
	private User createdBy;
	private Category category;
}
