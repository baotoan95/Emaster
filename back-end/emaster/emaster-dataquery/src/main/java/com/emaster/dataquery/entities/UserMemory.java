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

@Document(collection = "user_memories")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserMemory implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;

	@DBRef
	private Statement statement;
	@DBRef
	private User user;
	private long point;
	private Date startLearnDate;
	private long correctCount;
	private long incorrectCount;
	private Date lastLearnTime;

}
