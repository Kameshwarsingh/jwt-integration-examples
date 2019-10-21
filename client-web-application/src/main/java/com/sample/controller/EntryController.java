package com.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EntryController {
	 @RequestMapping("/")
    public String home(Model model) {
    	model.addAttribute("domain", "dev-776359.okta.com!");
    	model.addAttribute("clientId", "0oa1m2o3wch1iE7DJ357");
    	model.addAttribute("redirectUrl", "http://localhost:8080/authorization-code/callback");
    	//System.out.println("EntryController.home called");
        return "home";
    }
}
