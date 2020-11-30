package com.cts.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class TokenDetails {

	@NonNull
	private Long userId;
	@NonNull
	private String name;
	private String token;
	
	private Integer admin;
	
}
