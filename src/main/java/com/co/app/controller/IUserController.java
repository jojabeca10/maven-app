package com.co.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

public interface IUserController {
	
	@ResponseBody String parseToJwt(@RequestBody String data);
	ResponseEntity<String> saveUser(@RequestHeader("Authorization") String token,@RequestBody String data);
}
