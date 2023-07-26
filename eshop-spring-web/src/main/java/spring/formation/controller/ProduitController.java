package spring.formation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ProduitController {
	@GetMapping({"/produit/{id}" })
	public String test(@PathVariable(required = false) Integer id, Model model) {
		model.addAttribute("nb", id != null ? id * 2 : 0);

		return "produit";
	}
}
