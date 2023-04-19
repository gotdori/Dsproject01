package com.ds.project01.domain;



import com.ds.project01.dto.HobbyDataDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@IdClass(HobbyDataPK.class)
@Table(name="hobbyData_tb")
public class HobbyDataEntity {
	

	@Id
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity userEntiy;
	
	
	@Id
	@ManyToOne
	@JoinColumn(name = "hobby_cd")
	private HobbyEntity hobbyEntity;
	
	
	public static HobbyDataEntity toHobbyDataEntity(HobbyDataDto dto) {
		HobbyDataEntity hdEntity = new HobbyDataEntity();
		UserEntity userEntity = new UserEntity();
		HobbyEntity hobbyEntity = new HobbyEntity();
		
		userEntity.setUserId(dto.getUserId());
		hobbyEntity.setHobbyCd(dto.getHobbyCd());
		
		hdEntity.setUserEntiy(userEntity);
		hdEntity.setHobbyEntity(hobbyEntity);
		
		return hdEntity;
	}
}
