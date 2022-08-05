package com.co.app.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.co.app.controller.IUserController;
import com.co.app.service.IUserService;


@RestController
@RequestMapping("/app")
public class UserControllerImpl implements IUserController {
	
	@Autowired
	private IUserService iUserService;
	
	@Override
	@PostMapping(path = "/parseToJwt", 
	consumes = MediaType.APPLICATION_JSON_VALUE, 
	produces = MediaType.TEXT_PLAIN_VALUE)
	public @ResponseBody String parseToJwt(@RequestBody String json) {
		return iUserService.parseToJwt(json);
	}
	
	@Override
	@PostMapping(path = "/saveUser", 
	consumes = MediaType.APPLICATION_JSON_VALUE, 
	produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveUser(@RequestHeader("Authorization") String token,@RequestBody String data) {
		return iUserService.saveAndFlush(token, data);
	}
}
