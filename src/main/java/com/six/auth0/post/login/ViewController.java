package com.six.auth0.post.login;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Controller
public class ViewController {

	static Logger logger = LoggerFactory.getLogger(ViewController.class);

	@RequestMapping(value = "/add-user-name")
	public String index(@RequestParam(value = "session_token", required = false) String sessionToken, @RequestParam(value = "state", required = false) String state, Model model) {
		model.addAttribute("sessionToken", sessionToken);
		model.addAttribute("state", state);
		
		//sessionTokenUpdated(sessionToken, state, "Harjinder");

		return "add-user-name";
	}

}
