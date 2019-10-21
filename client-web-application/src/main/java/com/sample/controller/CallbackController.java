package com.sample.controller;

import java.util.Base64;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Controller
@RequestMapping(path = "/authorization-code")
public class CallbackController {
	

	
	@GetMapping(path = "/callback")
	public @ResponseBody ModelAndView callback(@RequestParam String code,HttpServletResponse response) {
		//Get access token [Exchange authorization-token for access-token] 
		String accessTokenStr=getAccessToken(code);
	
		//Extract JWT token ( access_token)
		JSONObject jsonObject = new JSONObject(accessTokenStr);
        String access_token = jsonObject.getString("access_token");
		
        //Set JWT in browser - cookie only accessible on https
        Cookie cookie = new Cookie("access_token", access_token);
        cookie.setDomain("localhost");
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        //cookie.setSecure(true);
        response.addCookie(cookie);
        
       
       return new ModelAndView("info");

		
	}
	

	
	private String getAccessToken(String authcode) {
		String clientId = "CLIENT-ID";
		String clientSecret = "CLIENT-SECRET";
		String domain = "YOUR-DOMAIN";
		String redirect_url = "http://localhost:8080/authorization-code/callback";
		String grant_type = "authorization_code";
		String scope = "openid";
		

		try {
			HttpResponse<String> response = Unirest.post("https://"+domain+"/oauth2/default/v1/token")
					.header("content-type", "application/x-www-form-urlencoded")
					.header("accept", "application/json")
					.header("authorization", getBearerToken(clientId,clientSecret))
					.body("grant_type=" + grant_type + "&code=" + authcode
							+ "&redirect_uri=" + redirect_url + "&scope=" + scope)
					.asString();
		
			String responseBody = response.getBody().toString();
			return responseBody;
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		return "#### Something not correct - Check logs for exception stack trace. ####";

	}
	
	private String getBearerToken(String clientId, String clientSecret) {
		String input = clientId + ":" + clientSecret;
		String encodedString = Base64.getEncoder().encodeToString(input.getBytes());
		return "Basic " + encodedString;
	}
	
}
