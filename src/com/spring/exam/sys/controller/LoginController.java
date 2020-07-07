package com.spring.exam.sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	
//	@GetMapping(value="/login")
//	public String login() {
//		return "login";
//	}
	
	@GetMapping(value="/login")
	public String loginPage(@RequestParam(required = false, name = "error", defaultValue = "false") String error,
							@RequestParam(required = false, name = "logout", defaultValue = "false") String logout,
							Model model) {
		
		String errorMessage = null;
		String successMessage = null;
        if(error.equals("true")) {
            errorMessage = "Username or Password is incorrect !!";
        }
        if(logout.equals("true")) {
        	successMessage = "You have been successfully logged out !!";
        }
		
        model.addAttribute("errorMessage", errorMessage);
        model.addAttribute("successMessage", successMessage);
		return "login";
	}
}
