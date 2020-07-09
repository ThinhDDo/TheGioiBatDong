package com.spring.exam.sys.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
	public String index(Model model, 
						Authentication auth,
						@ModelAttribute("mid") String filter) {
		
//		UserInfo userProfile = null;
//		if(auth == null) {
//			logger.info("AUTH IS NULL");
//		}
//		// Select Login user info
//		else {
//			if(auth.isAuthenticated()) {
//				User loginUser = (User) auth.getPrincipal();
//				userProfile = userService.selectUserByName(loginUser.getUsername());
//				logger.info("IS AUTHETICATED YEAHHHH!!!!!");			
//			}
//		}
//		model.addAttribute("profile", userProfile);s
		
		
		
		// Select All Brands
		List<Manufacturer> brands = manufacturerService.selectManufacturers();
		for(Manufacturer brand : brands) {
			logger.info("BRANDS NAME: " + brand.toString() + "\n");
		}
		model.addAttribute("brands", brands);
		
		// Select All Phones
		List<Phone> phones = null;
		if(filter.equals("")) {
			phones = phoneService.selectPhones();
			for(Phone phone : phones) {
				logger.info("PHONE NAME: " + phone.toString() + "\n");
			}
			model.addAttribute("phones", phones);
		} else {
			phones = phoneService.selectPhonesByManufacturer(filter);
			for(Phone phone : phones) {
				logger.info("PHONE NAME: " + phone.toString() + "\n");
			}
			model.addAttribute("phones", phones);
		}
		
		return "index";
	}
	
	@GetMapping(value="/filter")
	public RedirectView filter(@RequestParam(name = "mid", required = false, defaultValue = "") String mid,
						 RedirectAttributes redirectAttributes) {
		
		redirectAttributes.addAttribute("mid", mid);	
		
		return new RedirectView("index");
	}
}
