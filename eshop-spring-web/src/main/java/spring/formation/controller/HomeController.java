package spring.formation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
	
	@GetMapping("/home")
	public String home(Model model, @RequestParam(required = false) String username) {
		model.addAttribute("username", username);
		
		return "home";
	}
}
