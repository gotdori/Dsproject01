package com.ds.project01.dto;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HobbyDataDto {
	
	@NotBlank
	private String UserId;
	
	@NotBlank
	private String hobbyCd;
	
}
