package spring.formation.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
	@GetMapping("/")
	public String home(Model model, @RequestParam(required = false) String username) {
		model.addAttribute("username", username);
		model.addAttribute("dtJour", new Date());
		
		return "home";
	}
}
