package com.ds.project01.domain;


import com.ds.project01.dto.DeptDto;

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
@Table(name="dept_tb")
public class DeptEntity {
	

	@Id
	@Column(name = "dept_no", length = 300, nullable = false, unique = true)
	private String deptNo;
	
	@Column(name = "dept_nm", length = 300, nullable = false)
	private String deptNm;
	
	public static DeptEntity toDeptEntity(DeptDto dto) {
		DeptEntity entity = new DeptEntity();
		entity.setDeptNm(dto.getDeptNm());
		entity.setDeptNo(dto.getDeptNo());
		return entity;
	}
}
