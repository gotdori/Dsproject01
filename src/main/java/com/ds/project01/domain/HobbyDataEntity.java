package com.ds.project01.domain;



import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ds.project01.dto.HobbyDataDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@IdClass(HobbyDataPK.class)
@Table(name="hobbydata_tb")
public class HobbyDataEntity {
	

	@Id
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity userEntity;
	
	
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
		hdEntity.setUserEntity(userEntity);
		hdEntity.setHobbyEntity(hobbyEntity);
		
		System.out.println(hdEntity);
		
		return hdEntity;
	}
}
