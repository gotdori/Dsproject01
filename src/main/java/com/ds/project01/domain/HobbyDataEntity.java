package com.ds.project01.domain;


import com.ds.project01.dto.HobbyDataDto;
import com.ds.project01.dto.HobbyDto;

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
@Table(name="hobbyData_tb")
public class HobbyDataEntity {
	

	@Id
	@Column(name = "hobby_user_id", length = 20, nullable = false, unique = true)
	private String hobbyUserId;
	
	@Id
	@Column(name = "hobby_cd", length = 300, nullable = false)
	private String hobbyCd;
	
	public static HobbyDataEntity toHobbyDataEntity(HobbyDataDto dto) {
		HobbyDataEntity entity = new HobbyDataEntity();
		entity.setHobbyCd(dto.getHobbyCd());
		entity.setHobbyUserId(dto.getHobbyUserId());
		return entity;
	}
	
}
