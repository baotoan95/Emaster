package com.emaster.common.dto;

import java.io.Serializable;
import java.util.Date;
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

	private String createdByEmail;
	
	private int statementId;
	
	private int categoryId;
	
	private Date createdDate;
}
