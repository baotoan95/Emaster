package com.emaster.common.dto;

import java.util.List;

import com.emaster.common.enums.ChallengeType;

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
public class ChallengeDto {
	private ChallengeType challengeType;
	private List<StatementDto> questions;
	private int pointPerQuestion;
	private long timeout;
}
