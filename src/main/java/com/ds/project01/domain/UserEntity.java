package com.ds.project01.domain;

import com.ds.project01.dto.UserDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="user_tb")
public class UserEntity {
	

	@Id
	@NotNull
	@Column(name = "user_id", length = 20,  unique = true)
	public String userId;
	
	@NotNull
	@Column(name = "user_nm", length = 300)
	private String userNm;
	
	@NotNull
	@Column(name = "user_eml_addr", length = 320)
	private String userEmlAddr;
	
	@NotNull
	@Column(name = "user_telno", length = 13)
	private String userTelno;
	
	@NotNull
	@Column(name = "user_addr", length = 600)
	private String userAddr;
	
	@NotNull
	@Column(name = "user_aprv_yn", length = 1)
	private String userAprvYn;
	
	@ManyToOne
	@JoinColumn(name = "dept_no")
	private DeptEntity deptEntity;
	
	
	public static UserEntity toUserEntity(UserDto dto) {
		UserEntity entity = new UserEntity();
		
		DeptEntity deptEntity = new DeptEntity();
		deptEntity.setDeptNo(dto.getDeptNo());
		entity.setDeptEntity(deptEntity);
		
		entity.setUserId(dto.getUserId());
		entity.setUserNm(dto.getUserNm());
		entity.setUserAddr(dto.getUserAddr());
		entity.setUserEmlAddr(dto.getUserEmlAddr());
		entity.setUserTelno(dto.getUserTelno());
		entity.setUserAprvYn(dto.getUserAprvYn());
		return entity;
	}
	
}
