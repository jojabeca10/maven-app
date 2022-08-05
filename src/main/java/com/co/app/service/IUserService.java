package com.co.app.service;

import org.springframework.http.ResponseEntity;

public interface IUserService {
	ResponseEntity<String> saveAndFlush(String token, String data);
	String parseToJwt(String json);
}
