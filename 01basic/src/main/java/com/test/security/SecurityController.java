package com.test.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {
	
	@GetMapping("/all")
	public void doAll() {}
	
	@GetMapping("/member")
	public void doMember() {}
	
	@GetMapping("/admin")
	public void doAdmin() {}
	
	@GetMapping("/error/accessError")
	public void AccessError(Model model){
		model.addAttribute("msg","Access Denied Error...");
	}
	
	@GetMapping("/customlogin")
	public void Customlogin(String error, String logout , Model model) {
		System.out.println("ERROR : " + error);
		System.out.println("LOGOUT : " + logout);
		if(error!=null)
			model.addAttribute("error", "Login Error, Check Your Account");
		if(logout!=null)
			model.addAttribute("logout", "Logout Success");
	}
}
