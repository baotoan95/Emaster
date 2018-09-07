package com.emaster.common.dto;

import java.io.Serializable;
import java.util.Date;

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
public class UserMemoryDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3713568611825666839L;
	
	private StatementDto statement;
	private String userId;
	private long point;
	private Date startLearnDate;
	private long correctCount;
	private long incorrectCount;
	private Date lastLearnTime;
}
