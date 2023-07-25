package spring.formation.repo.jpa;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;
import java.util.UUID;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.formation.model.Fournisseur;
import spring.formation.repo.IFournisseurRepository;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class FournisseurRepositoryJpaTest {

	private static ClassPathXmlApplicationContext context;
	private static IFournisseurRepository repoFournisseur;

	@BeforeClass
	public static void start() {
		context = new ClassPathXmlApplicationContext("application-context.xml");
		repoFournisseur = context.getBean(IFournisseurRepository.class);
	}

	@AfterClass
	public static void end() {
		context.close();
	}

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
