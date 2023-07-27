package spring.formation.api;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import spring.formation.model.Fournisseur;
import spring.formation.model.Views;
import spring.formation.repo.IFournisseurRepository;

@RestController
@RequestMapping("/api/fournisseur")
public class FournisseurApi {

	private IFournisseurRepository fournisseurRepo;

	public FournisseurApi(IFournisseurRepository fournisseurRepo) {
		super();
		this.fournisseurRepo = fournisseurRepo;
	}

	@GetMapping("")
	@JsonView(Views.ViewFournisseur.class)
	public List<Fournisseur> list() {
		return this.fournisseurRepo.findAll();
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewFournisseurWithProduits.class)
	public Fournisseur find(@PathVariable("id") Long id) {
		Optional<Fournisseur> opt = this.fournisseurRepo.findById(id);

		if (opt.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fournisseur inconnu");
		}

		return opt.get();
	}

	@PostMapping("")
	@JsonView(Views.ViewFournisseur.class)
	@PreAuthorize("hasRole('ADMIN')")
	public Fournisseur create(@RequestBody Fournisseur fournisseur) {
		fournisseur = this.fournisseurRepo.save(fournisseur);

		return fournisseur;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewFournisseur.class)
	public Fournisseur update(@PathVariable Long id, @RequestBody Fournisseur fournisseur) {
		if (!id.equals(fournisseur.getId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erreur");
		}

		fournisseur = this.fournisseurRepo.save(fournisseur);

		return fournisseur;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if (!this.fournisseurRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Fournisseur inconnu");
		}

		this.fournisseurRepo.deleteById(id);
	}

}
