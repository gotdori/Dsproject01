package com.ds.project01.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Repository;

import com.ds.project01.dto.DeptDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name="dept_tb")
public class DeptEntity {
	

	@Id
	@NotNull
	@Column(name = "dept_no", length = 20, unique = true)
	private String deptNo;
	
	@NotNull
	@Column(name = "dept_nm", length = 300, unique = true)
	private String deptNm;
	
	
	public static DeptEntity toDeptEntity(DeptDto dto) {
		DeptEntity entity = new DeptEntity();
		entity.setDeptNm(dto.getDeptNm());
		entity.setDeptNo(dto.getDeptNo());
		return entity;
	}
}
