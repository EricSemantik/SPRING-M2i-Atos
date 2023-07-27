package spring.formation.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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

import spring.formation.api.dto.ConnexionRequest;
import spring.formation.api.dto.ConnexionResponse;
import spring.formation.config.jwt.JwtUtil;
import spring.formation.model.Utilisateur;
import spring.formation.model.Views;
import spring.formation.repo.IUtilisateurRepository;

@RestController
@RequestMapping("/api/utilisateur")
public class UtilisateurApi {

	@Autowired
	private IUtilisateurRepository utilisateurRepo;
	
	@Autowired // Par défaut, ce manager n'existe pas dans le contexte, donc on le configure dans SecurityConfig
	private AuthenticationManager authenticationManager;

	@PostMapping("/connexion")
	public ConnexionResponse connexion(@RequestBody ConnexionRequest connexionRequest) {
		// On va demander à SPRING SECURITY de vérifier le username / password
		// On a besoin d'un AuthenticationManager
		// On utilisera la méthode authenticate, qui attend un Authentication
		// Et on utilisera le type UsernamePasswordAuthenticationToken pour donner le username & le password
		Authentication authentication =
				new UsernamePasswordAuthenticationToken(connexionRequest.getUsername(), connexionRequest.getPassword());
		
		// On demande à SPRING SECURITY de vérifier ces informations de connexion
		this.authenticationManager.authenticate(authentication);
		
		// Si on arrive ici, c'est que la connexion a fonctionné
		ConnexionResponse response = new ConnexionResponse();
		
		// On génère un jeton pour l'utilisateur connecté
		String token = JwtUtil.generate(authentication);
		
		response.setSuccess(true);
		response.setToken(token); // On donne le jeton en réponse
		
		return response;
	}
	
	@GetMapping("")
	@JsonView(Views.ViewUtilisateur.class)
	public List<Utilisateur> list() {
		return this.utilisateurRepo.findAll();
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewUtilisateur.class)
	public Utilisateur find(@PathVariable("id") Long id) {
		Optional<Utilisateur> opt = this.utilisateurRepo.findById(id);

		if (opt.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur inconnu");
		}

		return opt.get();
	}

	@PostMapping("")
	@JsonView(Views.ViewUtilisateur.class)
	public Utilisateur create(@RequestBody Utilisateur utilisateur) {
		utilisateur = this.utilisateurRepo.save(utilisateur);

		return utilisateur;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewUtilisateur.class)
	public Utilisateur update(@PathVariable Long id, @RequestBody Utilisateur utilisateur) {
		if (!id.equals(utilisateur.getId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erreur");
		}

		utilisateur = this.utilisateurRepo.save(utilisateur);

		return utilisateur;
	}


	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		if(!this.utilisateurRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Utilisateur inconnu");
		}
		
		this.utilisateurRepo.deleteById(id);
	}

}
