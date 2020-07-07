package com.spring.exam.sys.controller;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.spring.exam.sys.model.Manufacturer;
import com.spring.exam.sys.model.Phone;
import com.spring.exam.sys.model.UserInfo;
import com.spring.exam.sys.service.ManufacturerService;
import com.spring.exam.sys.service.PhoneService;
import com.spring.exam.sys.service.UserService;

@Controller
public class HomeController {
	
	private static final Logger logger = Logger.getLogger(HomeController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ManufacturerService manufacturerService;
	
	@Autowired
	private PhoneService phoneService;
	
	/**
	 * Welcome page, Profile page
	 * @param model
	 * @return
	 */
	@GetMapping(value= {"/", "/index"})
	public String profile(Model model, Authentication auth) {
		User loginUser = (User) auth.getPrincipal();
		
		// Select All Brands
		List<Manufacturer> brands = manufacturerService.selectManufacturers();
		for(Manufacturer brand : brands) {
			logger.info("BRANDS NAME: " + brand.toString() + "\n");
		}
		model.addAttribute("brands", brands);
		
		// Select All Phones
		List<Phone> phones = phoneService.selectPhones();
		for(Phone phone : phones) {
			logger.info("PHONE NAME: " + phone.toString() + "\n");
		}
		model.addAttribute("phones", phones);
		
		// Select Login user info
		UserInfo userProfile = userService.selectUserByName(loginUser.getUsername());
		model.addAttribute("profile", userProfile);
		
		return "index";
	}
	
}
