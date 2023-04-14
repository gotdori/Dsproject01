package com.ds.project01.domain;

import com.ds.project01.dto.UserDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="user_tb")
public class UserEntity {
	

	@Id
	@Column(name = "user_id", length = 20, nullable = false, unique = true)
	private String userId;
	
	@Column(name = "user_nm", length = 300, nullable = false)
	private String userNm;
	
	@Column(name = "user_eml_addr", length = 320, nullable = false)
	private String userEmlAddr;
	
	@Column(name = "user_dept_no", length = 20, nullable = false)
	private String userDeptNo;
	
	@Column(name = "user_telno", length = 13, nullable = false)
	private String userTelno;
	
	@Column(name = "user_addr", length = 600, nullable = false)
	private String userAddr;
	
	@Column(name = "user_aprv_yn", length = 1, nullable = false)
	private String userAprvYn;
	
	public static UserEntity toUserEntity(UserDto dto) {
		UserEntity entity = new UserEntity();
		entity.setUserId(dto.getUserId());
		entity.setUserNm(dto.getUserNm());
		entity.setUserAddr(dto.getUserAddr());
		entity.setUserEmlAddr(dto.getUserEmlAddr());
		entity.setUserDeptNo(dto.getUserDeptNo());
		entity.setUserTelno(dto.getUserTelno());
		entity.setUserAprvYn(dto.getUserAprvYn());
		return entity;
	}
	
}
