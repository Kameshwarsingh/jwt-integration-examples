package com.sample.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EntryController {
	 @RequestMapping("/")
    public String home(Model model) {
        return "home";
    }
}
