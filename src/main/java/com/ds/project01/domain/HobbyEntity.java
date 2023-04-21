package com.ds.project01.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="hobby_tb")
public class HobbyEntity {
	
	
	@Id
	@NotNull
	@Column(name = "hobby_cd", length = 300, unique = true)
	public String hobbyCd;
	
	@NotNull
	@Column(name = "hobby_nm", length = 300, unique = true)
	private String hobbyNm;
	
}
