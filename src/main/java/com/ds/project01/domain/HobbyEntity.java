package com.ds.project01.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
	@Column(name = "hobby_cd_nm", length = 300, nullable = false, unique = true)
	private String hobbyCdnm;
	
	@Column(name = "hobby_nm", length = 300, nullable = false)
	private String hobbyNM;
	
}
