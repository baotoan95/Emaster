package com.emaster.common.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserMemoryDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2119965770456793942L;
	private String userId;
	private String statementId;
}
