package spring.formation.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import spring.formation.controller.validator.CommentaireValidator;
import spring.formation.model.Commentaire;
import spring.formation.repo.ICommentaireRepository;

@Controller
@RequestMapping("/commentaire")
public class CommentaireController {
	@Autowired
	private ICommentaireRepository commentaireRepo;
	
	@GetMapping({ "", "/list" }) // ETAPE 1
	public String list(Model model) {
		List<Commentaire> commentaires = commentaireRepo.findAll(); // ETAPE 2

		model.addAttribute("mesCommentaires", commentaires); // ETAPE 3

		return "commentaire/list"; // ETAPE 4
	}
	
	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("commentaire", new Commentaire());
		
		return "commentaire/form";
	}
	
	@GetMapping("/edit")
	public String edit(@RequestParam Long id, Model model) {
		Commentaire commentaire = commentaireRepo.findById(id).get();
		
		model.addAttribute("commentaire", commentaire);
		
		return "commentaire/form";
	}
	
	@PostMapping("/save")
	public String save(@RequestParam(required = false) Long id, @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm") @RequestParam Date date, @RequestParam int note, @RequestParam String commentaire) {
		
		Commentaire comment = new Commentaire(id, date, note, commentaire);
		
		commentaireRepo.save(comment);

		return "redirect:list";
	}
	
	@PostMapping("/saveBis")
	public String saveBis(@ModelAttribute("commentaire") @Valid Commentaire commentaire, BindingResult result) {
		new CommentaireValidator().validate(commentaire, result);
		
		if(result.hasErrors()) {
			return "commentaire/form";
		}
		
		commentaireRepo.save(commentaire);
		
		return "redirect:list";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		commentaireRepo.deleteById(id);
		
		return "forward:/commentaire/list";
	}
}
