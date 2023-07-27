package spring.formation.api;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
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

import spring.formation.model.Produit;
import spring.formation.model.Views;
import spring.formation.repo.IProduitRepository;

@RestController
@RequestMapping("/api/produit")
public class ProduitApi {

	private IProduitRepository produitRepo;

	public ProduitApi(IProduitRepository produitRepo) {
		super();
		this.produitRepo = produitRepo;
	}

	@GetMapping("")
	@JsonView(Views.ViewProduit.class)
	public List<Produit> list() {
		return this.produitRepo.findAll();
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewProduit.class)
	public Produit find(@PathVariable("id") Long id) {
		Optional<Produit> opt = this.produitRepo.findById(id);

		if (opt.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit inconnu");
		}

		return opt.get();
	}

	@PostMapping("")
	@JsonView(Views.ViewProduit.class)
	public Produit create(@RequestBody Produit produit) {
		produit = this.produitRepo.save(produit);

		return produit;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewProduit.class)
	public Produit update(@PathVariable Long id, @RequestBody Produit produit) {
		if (!id.equals(produit.getId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erreur");
		}

		produit = this.produitRepo.save(produit);

		return produit;
	}


	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if(!this.produitRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produit inconnu");
		}
		
		this.produitRepo.deleteById(id);
	}

}
