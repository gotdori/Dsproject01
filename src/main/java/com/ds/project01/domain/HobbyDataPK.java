package com.ds.project01.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HobbyDataPK implements Serializable{
	
	private UserEntity userEntiy;
	
	private HobbyEntity hobbyEntity;
	

}