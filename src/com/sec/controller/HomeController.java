package com.sec.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sec.model.User;
import com.sec.repository.UserRepository;

@Controller
public class HomeController {

	@Autowired
	UserRepository uRep;
	
	@RequestMapping(value = {"/", "/welcome"})
	public ModelAndView getHome(Model model, Principal principal) {
		System.out.println("in getHome");
		ModelAndView mv = new ModelAndView("home");
		
		User user = uRep.findByUsername(principal.getName());
		mv.addObject("user", user);
		return mv;
		
	}
	
	@RequestMapping("/subscriber")
	public ModelAndView getSubscriber() {
		
		ModelAndView mv = new ModelAndView("subscriber");
		return mv;
	}
	
	@RequestMapping("/contactus")
	public ModelAndView getContact() {
		System.out.println("in getContact");
		ModelAndView mv = new ModelAndView("contact");
		return mv;
	}
	
	@RequestMapping("/admin")
	public ModelAndView getAdmin() {
		System.out.println("in getAdmin");
		ModelAndView mv = new ModelAndView("admin");
		return mv;
	}
	@RequestMapping("/all")
	public ModelAndView getSudo() {
		System.out.println("in getSudo");
		ModelAndView mv = new ModelAndView("sudo");
		return mv;
	}
	
	@RequestMapping("/403")
	public ModelAndView get403(Principal principal) {
		System.out.println("in get403");
		ModelAndView mv = null;
		if(principal != null) {
			mv = new ModelAndView("403");
			mv.addObject("message", "Hi " + principal.getName() + ", you don't have access to this page");
		}else {
			mv.addObject("message", "You don't have access to this page!" );
			
		}
		return mv;
	}

}
