package com.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping(path = "/data")
public class DataController {

	@GetMapping(path = "/users")
	public @ResponseBody String getUsers(@RequestHeader("Authorization") String authorization) {
			return "[{\'user-name\':\'test-user1\'},{\'user-name\':\'test-user2\'}]";
	}
}
