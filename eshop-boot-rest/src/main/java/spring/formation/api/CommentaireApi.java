package spring.formation.api;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import spring.formation.model.Commentaire;
import spring.formation.repo.ICommentaireRepository;

@RestController
@RequestMapping("/api/commentaire")
public class CommentaireApi {

	private ICommentaireRepository commentaireRepo;

	public CommentaireApi(ICommentaireRepository commentaireRepo) {
		super();
		this.commentaireRepo = commentaireRepo;
	}

	@GetMapping("")
	public List<Commentaire> list() {
		return this.commentaireRepo.findAll();
	}

	@GetMapping("/{id}")
	public Commentaire find(@PathVariable("id") Long id) {
		Optional<Commentaire> opt = this.commentaireRepo.findById(id);

		if (opt.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Commentaire inconnu");
		}

		return opt.get();
	}

	@PostMapping("")
	public Commentaire create(@RequestBody Commentaire commentaire) {
		commentaire = this.commentaireRepo.save(commentaire);

		return commentaire;
	}

	@PutMapping("/{id}")
	public Commentaire update(@PathVariable Long id, @RequestBody Commentaire commentaire) {
		if (!id.equals(commentaire.getId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erreur");
		}

		commentaire = this.commentaireRepo.save(commentaire);

		return commentaire;
	}

	@PatchMapping("/{id}")
	public Commentaire partialUpdate(@PathVariable Long id, @RequestBody Map<String, Object> fields) {
		Optional<Commentaire> opt = this.commentaireRepo.findById(id);

		if (opt.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Commentaire inconnu");
		}

		Commentaire commentaire = opt.get();

		if (fields.containsKey("date")) {
			commentaire.setDate((Date) fields.get("date"));
		}
		if (fields.containsKey("note")) {
			commentaire.setNote((int) fields.get("note"));
		}
		if (fields.containsKey("commentaire")) {
			commentaire.setCommentaire((String) fields.get("commentaire"));
		}

		commentaire = this.commentaireRepo.save(commentaire);

		return commentaire;
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if(!this.commentaireRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Commentaire inconnu");
		}
		
		this.commentaireRepo.deleteById(id);
	}

}
