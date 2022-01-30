package com.six.auth0.post.login;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@RestController
public class WSControler {

	static Logger logger = LoggerFactory.getLogger(WSControler.class);

	@GetMapping("/update-token-with-name")
	public String greeting(@RequestParam(value = "session_token", required = false) String sessionToken, @RequestParam(value = "state", required = false) String state,
			@RequestParam(value = "full_name", required = false) String fullName) {
		try {

			Algorithm algorithm = Algorithm.HMAC256("SIX_POST_LOGIN_SECRET");
			JWTVerifier verifier = JWT.require(algorithm).withIssuer("malotian-lab.auth0.com").build(); // Reusable verifier instance
			DecodedJWT jwt = verifier.verify(sessionToken);
			logger.info("{}", jwt);
			String token = JWT.create().withSubject(jwt.getSubject()).withAudience("six-dashboard-application").withIssuer("six-dashboard-application").withIssuedAt(new Date(System.currentTimeMillis()))
					.withClaim("full_name", fullName).withClaim("email", jwt.getClaim("email").asString()).withClaim("state", state).withExpiresAt(new Date(System.currentTimeMillis() + (5 * 60 * 1000))).sign(algorithm);
			logger.info("{}", token);
			return token;
		} catch (JWTVerificationException exception) {
			exception.printStackTrace();
		}
		return sessionToken;
	}

}
