package com.emaster.dataquery.entities;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "question_bank")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionBank implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@DBRef
	private Statement statement;
	@DBRef
	private User createdBy;
	@DBRef
	private Category category;
	private Date createdDate;

}
