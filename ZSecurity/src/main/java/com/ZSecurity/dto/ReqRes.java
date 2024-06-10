package com.ZSecurity.dto;

import java.util.List;

import com.ZSecurity.entity.OurUsers;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude
@JsonIgnoreProperties
public class ReqRes {

	private int statusCode;
	private String error;
	private String message;
	private String token;
	private String refreshToken;
	private String expirationTime;
	private String name;
	private String nombrebar;
	private String role;
	private String email;
	private String password;
	private OurUsers ourUsers;
	private List<OurUsers> ourUsersList;
	
	
}
