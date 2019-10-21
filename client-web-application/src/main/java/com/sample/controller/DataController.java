package com.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

@Controller
@RequestMapping(path = "/data")
public class DataController {

	@RequestMapping("/info")
	public String info(Model model) {
		return "info";
	}

	@RequestMapping("/users")
	public ModelAndView users(@CookieValue("access_token") String access_token) {
		
		//Retrieve access-token from cookie and call resource server for data
		String data = callResourceServer(access_token);
		
		//render data from resource server to ModelView
		ModelAndView modelAndView = new ModelAndView("data");
		modelAndView.addObject("data", data);
		return modelAndView;
	}

	private String callResourceServer(String accessToken) {
		System.out.println("DataController.callResourceServer called - ");
		try {
			HttpResponse<String> response = Unirest.get("http://localhost:8000/data/users").header("authorization", "Bearer " + accessToken).asString();
			System.out.println("DataController.callResourceServer Status code - " + response.getStatus());
			return response.getBody().toString();
		} catch (UnirestException e) {
			e.printStackTrace();
		}
		
		return "#### Something not correct - 1) Ensure resource server is running 2)Check logs for exception stack trace. ####";

	}
	
	

}
