package com.emaster.dataquery.entities;

import java.io.Serializable;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.emaster.dataquery.enums.UserRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	private ObjectId id;

	@Indexed(unique = true)
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
