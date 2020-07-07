package com.spring.exam.sys.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.spring.exam.sys.model.UserInfo;
import com.spring.exam.sys.service.UserService;

@Controller
public class ProfileController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ServletContext servletContext;
	
	private static String uploadLocation = System.getProperty("user.dir") + "//WebContent//WEB-INF//views//img//";
	
	@GetMapping(value="/profile")
	public String profile(
			@RequestParam(name = "user", required = false, defaultValue = "") String username,
			@RequestParam(name = "updated", required = false, defaultValue = "") String update,
			@RequestParam(name = "avata", required = false, defaultValue = "") String avata,
			Model model) {
		
		UserInfo userInfo = null;
		if(!username.equals("")) {
			userInfo = userService.selectUserByName(username);		
			model.addAttribute("profile", userInfo);
		}
		
		if(!update.equals("")) {
			model.addAttribute("successMessage", "Saved successfully");
		}
		
		return "profile";
	}
	
	@PostMapping(value="/save-avata")
	public RedirectView updateAvata(
					@RequestParam(value = "username", required = false) String username,
					@RequestParam(value = "file") MultipartFile[] file,
					RedirectAttributes redirectAttributes,
					Model model) {
		
//		Path fileNameAndPath = Paths.get(uploadLocation, file[0].getOriginalFilename());
//		try {
//			Files.write(fileNameAndPath, file[0].getBytes());
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		File serverFile = new File(servletContext.getRealPath("/WEB-INF/views/img/") + file[0].getOriginalFilename());
		File uploadFile = new File(uploadLocation + file[0].getOriginalFilename());
		try {
			file[0].transferTo(serverFile);
			file[0].transferTo(uploadFile);
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String avata = file[0].getOriginalFilename();
		redirectAttributes.addAttribute("avata", avata);
		redirectAttributes.addAttribute("user", username);
		
		UserInfo user = userService.selectUserByName(username);
		
		File f = new File(uploadLocation  + user.getAvata()); 
		if(f.exists()) {
			f.delete();
		}
		
		user.setAvata(avata);
		userService.updateUser(user);
		
		return new RedirectView("profile");
	}
	
	@PostMapping(value="/save-profile")
	public String updateProfile(@ModelAttribute("profile") UserInfo user) {
		
		userService.updateUser(user);
		return "redirect:/profile?updated=success&user=" + user.getUsername();
	}
}
