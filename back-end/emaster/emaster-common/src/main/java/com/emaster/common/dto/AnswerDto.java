package com.emaster.common.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4315411925677807275L;
	private String id;
	private String content;
	private String sound;
	private String slowSound;
	private String image;
	private boolean correct;
}
