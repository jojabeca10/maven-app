package com.co.app.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.co.app.entity.ITelefonoRepository;
import com.co.app.entity.IUsuarioRepository;
import com.co.app.entity.Response;
import com.co.app.entity.Telefono;
import com.co.app.entity.Usuario;
import com.co.app.service.IUserService;
import com.co.app.util.UtilService;
import com.google.gson.Gson;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private UtilService utilService;
	@Autowired
    private IUsuarioRepository iUsuarioRepository;
	@Autowired
    private ITelefonoRepository iTelefonoRepository;
	
	private static final String REGEX = "^(.+)@(.+)$";
	
	public Usuario restaurateValues(Usuario usr) {
		usr.setEmail(null);
		usr.setName(null);
		usr.setPassword(null);
		usr.setPhones(null);
		return usr;
	}
	
	public Response addMessage(Response rsp, String message) {
		log.info(message);
		rsp.setMensaje(message);
		return rsp;
	}
	
	public void saveTelephoneUsr(Usuario usr) {
		for (Telefono telephone: usr.getPhones()) {
			telephone.setIdUsr(usr.getId());
			iTelefonoRepository.save(telephone);
		}
	}

	@Override
	public ResponseEntity<String> saveAndFlush(String token, String data) {
		Pattern pattern = Pattern.compile(REGEX);
		Response response = new Response();
		Gson gson = new Gson();
		Usuario usr = null;
		
		try {
			if(Optional.ofNullable(token).isPresent()) {
				String[] arraytoken = token.replace(" ", ",").split(",");
				if(arraytoken.length > 1) {
					String body = utilService.decodeToken(token);
					if(!body.isEmpty() && body.equals(data)) {
						usr = gson.fromJson(data, Usuario.class);
						Matcher matcher = pattern.matcher(usr.getEmail());
						if(matcher.matches()) {
							Usuario findUsr = iUsuarioRepository.findByEmail(usr.getEmail());
							if(!Optional.ofNullable(findUsr).isPresent()) {
								usr.setCreated(new Date());
								usr.setLastLogin(new Date());
								usr.setToken(token);
								iUsuarioRepository.save(usr);
								saveTelephoneUsr(usr);
							}
							else {
								return new ResponseEntity<String>(gson.toJson(addMessage(response,"Correo ya registrado")),
						                HttpStatus.CONFLICT);
							}
						}
						else {
							return new ResponseEntity<String>(gson.toJson(addMessage(response,"Email incorrect.")),
					                HttpStatus.FORBIDDEN);
						}
					}
					else {
						return new ResponseEntity<String>(gson.toJson(addMessage(response,"Authorization token not match.")),
				                HttpStatus.UNAUTHORIZED);
					}
				}
				else {
					return new ResponseEntity<String>(gson.toJson(addMessage(response,"Token not valid.")),
			                HttpStatus.UNAUTHORIZED);
				}
			}
		} catch (Exception e) {
			log.error("Exception.." + e.getMessage());
			return new ResponseEntity<String>("Exception. " + e.getMessage(),
	                HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<String>(gson.toJson(restaurateValues(usr)),
                HttpStatus.OK);
	}

	@Override
	public String parseToJwt(String json) {
		return utilService.parseToJwt(json);
	}

}
