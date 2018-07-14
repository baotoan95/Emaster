package com.emaster.common.dto;

import java.io.Serializable;
import java.util.Date;

import com.emaster.common.enums.UserRole;

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
public class UserDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String email;

	private String password;

	private String displayName;

	private String socialNetwork;

	private String gender;

	private String bio;

	private String currentLevel;

	private String avatar;

	private String targetDaily;

	private int remindTime;

	private String location;

	private String nativeLang;

	private String learningLang;

	private long curentPoint;

	private Date createDate;

	private UserRole role;
}
