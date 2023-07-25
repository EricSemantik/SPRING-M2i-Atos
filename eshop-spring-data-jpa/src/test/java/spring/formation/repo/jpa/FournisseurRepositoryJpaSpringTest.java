package spring.formation.repo.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.UUID;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import spring.formation.model.Fournisseur;
import spring.formation.repo.IFournisseurRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
//@ContextConfiguration(classes = ApplicationConfig.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Rollback(true)
public class FournisseurRepositoryJpaSpringTest {

	@Autowired
	private IFournisseurRepository repoFournisseur;

	@Test
	public void testFindAll() {
		// ARRANGE
		int startSize = repoFournisseur.findAll().size();

		Fournisseur fournisseur1 = new Fournisseur();
		fournisseur1.setResponsable("FOUR1");
		repoFournisseur.save(fournisseur1);
		Fournisseur fournisseur2 = new Fournisseur();
		fournisseur2.setResponsable("FOUR2");
		repoFournisseur.save(fournisseur2);

		// ACT
		int endSize = repoFournisseur.findAll().size();

		// ASSERT
		assertEquals(2, endSize - startSize);
	}

	@Test
	public void testFindById() {
		// ARRANGE
		Fournisseur fournisseur1 = new Fournisseur();
		fournisseur1.setResponsable("FOUR1");
		fournisseur1 = repoFournisseur.save(fournisseur1);

		// ACT
		Fournisseur fournisseur = repoFournisseur.findById(fournisseur1.getId()).get();

		// ASSERT
		assertNotNull(fournisseur);
		assertNotNull(fournisseur.getProduits());
	}

	@Test
	public void shouldAdd() {
		Fournisseur fournisseur = new Fournisseur();

		fournisseur.setNom("F1");
		fournisseur.setResponsable("RESP");

		fournisseur = repoFournisseur.save(fournisseur);

		fournisseur.setNom("F2"); // detached

		fournisseur = repoFournisseur.save(fournisseur);

		assertNotEquals(Long.valueOf(0), fournisseur.getId());
	}

	@Test
	public void shouldUpdate() {
		Long fournisseurId = this.getLastId();
		String fournisseurNom = UUID.randomUUID().toString();
		Fournisseur fournisseur = repoFournisseur.findById(fournisseurId).get();

		fournisseur.setNom(fournisseurNom);
		repoFournisseur.save(fournisseur);

		fournisseur = repoFournisseur.findById(fournisseurId).get();

		assertNotNull(fournisseur);
		assertEquals(fournisseurId, fournisseur.getId());
		assertEquals(fournisseurNom, fournisseur.getNom());
	}

	@Test
	public void testDeleteById() {
		// ARRANGE
		Fournisseur fournisseur1 = new Fournisseur();
		fournisseur1.setResponsable("FOUR1");
		fournisseur1 = repoFournisseur.save(fournisseur1);

		int startSize = repoFournisseur.findAll().size();

		// ACT
		repoFournisseur.deleteById(fournisseur1.getId());

		int endSize = repoFournisseur.findAll().size();
		assertEquals(-1, endSize - startSize);
	}

	private Long getLastId() {
		List<Fournisseur> fournisseurs = repoFournisseur.findAll();
		return fournisseurs.get(fournisseurs.size() - 1).getId();
	}
}
