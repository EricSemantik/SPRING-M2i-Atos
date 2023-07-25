package spring.formation.repo.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import spring.formation.model.Fournisseur;
import spring.formation.repo.IFournisseurRepository;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FournisseurRepositoryJpaTest {
	private IFournisseurRepository repoFournisseur = new FournisseurRepositoryJpa();
	
	@Test
	public void testFindAll() {
		List<Fournisseur> fournisseurs = this.repoFournisseur.findAll();

		assertNotNull(fournisseurs);
		assertNotEquals(0, fournisseurs.size());
		assertNotEquals(Long.valueOf(0), fournisseurs.get(0).getId());
		assertNotNull(fournisseurs.get(0).getNom());
	}
	
//	@Test
	public void testFindById() {
		Long fournisseurId = 1L;
		Fournisseur fournisseur = this.repoFournisseur.findById(fournisseurId).get();

		assertNotNull(fournisseur);
		assertNotNull(fournisseur.getProduits());
		assertNotEquals(0, fournisseur.getProduits().size());
	}
	
//	@Test
	public void shouldAdd() {
		Fournisseur fournisseur = new Fournisseur();
		
		fournisseur.setNom("F1");
		fournisseur.setResponsable("RESP");
		
		fournisseur = this.repoFournisseur.save(fournisseur);

		assertNotEquals(Long.valueOf(0), fournisseur.getId());
	}
	
//	@Test
	public void shouldUpdate() {
		Long fournisseurId = this.getLastId();
		String fournisseurNom = UUID.randomUUID().toString();
		Fournisseur fournisseur = this.repoFournisseur.findById(fournisseurId).get();
		
		fournisseur.setNom(fournisseurNom);
		this.repoFournisseur.save(fournisseur);
		
		fournisseur = this.repoFournisseur.findById(fournisseurId).get();

		assertNotNull(fournisseur);
		assertEquals(fournisseurId, fournisseur.getId());
		assertEquals(fournisseurNom, fournisseur.getNom());
	}
	
//	@Test
	public void testDeleteById() {
		Long fournisseurId = this.getLastId();
		this.repoFournisseur.deleteById(fournisseurId);
		
		Optional<Fournisseur> optFournisseur = this.repoFournisseur.findById(fournisseurId);

		assertNotNull(optFournisseur);
		assertFalse(optFournisseur.isPresent());
	}
	
	private Long getLastId() {
		List<Fournisseur> fournisseurs = this.repoFournisseur.findAll();
		return fournisseurs.get(fournisseurs.size() - 1).getId();
	}
}
