package spring.formation.repo.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.formation.config.ApplicationConfig;
import spring.formation.model.Produit;
import spring.formation.repo.IProduitRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class ProduitRepositoryJpaTest {

	@Autowired
	private IProduitRepository repoProduit;

	@Test
	public void testFindAll() {
		int startSize = repoProduit.findAll().size();

		Produit produit = new Produit("NEW");

		produit = repoProduit.save(produit);

		int endSize = repoProduit.findAll().size();

		assertEquals(1, endSize - startSize);
	}

	@Test
	public void testFindById() {
		Produit produit = new Produit("NEW");
		produit = repoProduit.save(produit);

		Produit produitFind = this.repoProduit.findById(produit.getId()).get();

		assertNotNull(produitFind);
		assertNotNull(produitFind.getDetails());
	}

	@Test
	public void shouldAdd() {
		Produit produit = new Produit("NEW");

		produit.setPrixAchat(10d);
		produit.setPrixVente(100d);
		produit.setModele("MOD");
		produit.setReference("REF");

		produit = this.repoProduit.save(produit);

		Produit produitFind = this.repoProduit.findById(produit.getId()).get();

		assertEquals((Double) 10.0, produitFind.getPrixAchat());
		assertEquals((Double) 100.0, produitFind.getPrixVente());
		assertEquals("MOD", produitFind.getModele());
		assertEquals("REF", produitFind.getReference());
	}

	@Test
	public void shouldUpdate() {
		Produit produit = new Produit("NEW");

		produit.setPrixAchat(10d);
		produit.setPrixVente(100d);
		produit.setModele("MOD");
		produit.setReference("REF");

		produit = this.repoProduit.save(produit);
		
		String produitNom = UUID.randomUUID().toString();
		Produit produitFind = this.repoProduit.findById(produit.getId()).get();

		produitFind.setLibelle(produitNom);
		produitFind = this.repoProduit.save(produitFind);

		produitFind = this.repoProduit.findById(produitFind.getId()).get();

		assertNotNull(produitFind);
		assertEquals(produitNom, produitFind.getLibelle());
	}

	@Test
	public void testDeleteById() {
		Produit produit = new Produit("NEW");

		produit.setPrixAchat(10d);
		produit.setPrixVente(100d);
		produit.setModele("MOD");
		produit.setReference("REF");

		produit = this.repoProduit.save(produit);
		
		int startSize = repoProduit.findAll().size();
		
		this.repoProduit.deleteById(produit.getId());

		int endSize = repoProduit.findAll().size();
		assertEquals(-1, endSize - startSize);
	}


}
