package spring.formation.repo.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.Test;

import spring.formation.model.Fournisseur;
import spring.formation.model.Produit;
import spring.formation.repo.IProduitRepository;

public class ProduitRepositoryJpaTest {
	private IProduitRepository repoProduit = new ProduitRepositoryJpa();
	
	@Test
	public void testFindAll() {
		List<Produit> produits = this.repoProduit.findAll();

		assertNotNull(produits);
		assertNotEquals(0, produits.size());
		assertNotEquals(Long.valueOf(0), produits.get(0).getId());
		assertNotNull(produits.get(0).getLibelle());
	}
	
//	@Test
	public void testFindById() {
		Long produitId = 1L;
		Produit produit = this.repoProduit.findById(produitId).get();

		assertNotNull(produit);
		assertNotNull(produit.getDetails());
	}
	
//	@Test
	public void shouldAdd() {
		Produit produit = new Produit("NEW");
		
		produit.setPrixAchat(10d);
		produit.setPrixVente(100d);
		produit.setFournisseur(new Fournisseur());
		produit.getFournisseur().setId(1L);
		produit.setModele("MOD");
		produit.setReference("REF");
		
		produit = this.repoProduit.save(produit);

		assertNotEquals(Long.valueOf(0), produit.getId());
	}
	
//	@Test
	public void shouldUpdate() {
		Long produitId = this.getLastId();
		String produitNom = UUID.randomUUID().toString();
		Produit produit = this.repoProduit.findById(produitId).get();
		
		produit.setLibelle(produitNom);
		this.repoProduit.save(produit);
		
		produit = this.repoProduit.findById(produitId).get();

		assertNotNull(produit);
		assertEquals(produitId, produit.getId());
		assertEquals(produitNom, produit.getLibelle());
	}
	
//	@Test
	public void testDeleteById() {
		Long produitId = this.getLastId();
		this.repoProduit.deleteById(produitId);
		
		Optional<Produit> optProduit = this.repoProduit.findById(produitId);

		assertNotNull(optProduit);
		assertFalse(optProduit.isPresent());
	}
	
	private Long getLastId() {
		List<Produit> fournisseurs = this.repoProduit.findAll();
		return fournisseurs.get(fournisseurs.size() - 1).getId();
	}
}
