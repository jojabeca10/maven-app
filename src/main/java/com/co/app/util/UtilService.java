package com.co.app.util;

import java.util.Base64;
import java.util.HashMap;
import javax.crypto.SecretKey;
import org.springframework.stereotype.Component;

import com.co.app.entity.Usuario;
import com.nimbusds.jose.JWEAlgorithm;
import com.nimbusds.jose.Payload;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class UtilService {
	
	public String parseToJwt(String json) {
		JwtBuilder tokenJWT = null;
		try {
			Payload payload = new Payload(json.getBytes());
			JWEAlgorithm alg = JWEAlgorithm.RSA_OAEP_256;
			SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
			HashMap<String, Object> header = new HashMap<String,Object>();
			header.put("alg", alg.getName());
			header.put("typ","JWT");
			
			tokenJWT = Jwts
					.builder()
					.setHeader(header)
					.setPayload(payload.toString())
					.signWith(key);
		}
		catch (Exception e) {
			log.error("Exception generate. " + e.getMessage());
		}
		log.info("parseToJwt correctly...");
		return "Bearer " + tokenJWT.compact();
	}
	
	public String decodeToken(String token) {
		try {
			String[] chunks = token.split("\\.");
			if(chunks.length > 2) {
				Base64.Decoder decoder = Base64.getUrlDecoder();
				return new String(decoder.decode(chunks[1]));
			}
		} catch (Exception e) {
			log.error("Exception generate. " + e.getMessage());
		}
		return "";
	}

}
