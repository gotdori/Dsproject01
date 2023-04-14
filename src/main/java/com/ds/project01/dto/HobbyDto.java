package com.ds.project01.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HobbyDto {
	
	@NotBlank
	private String hobbyCd;
	
	@NotBlank
	private String hobbyUserId;
	
}
