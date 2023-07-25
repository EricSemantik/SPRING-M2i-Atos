package spring.formation.repo.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import spring.formation.model.Produit;
import spring.formation.repo.IProduitRepository;

public class ProduitRepositoryJpa implements IProduitRepository {
	protected final static EntityManagerFactory emf = Persistence.createEntityManagerFactory("EshopUnit");

	public ProduitRepositoryJpa() {
	}

	public List<Produit> findAll() {
		EntityManager em = emf.createEntityManager();

		try {
			return em.createQuery("select p from Produit p", Produit.class).getResultList();
		}

		catch (Exception ex) {
			ex.printStackTrace();
			return new ArrayList<>();
		}

		finally {
			em.close();
		}
	}

	public Optional<Produit> findById(Long id) {
		EntityManager em = emf.createEntityManager();

		try {
			return Optional.of(em.createQuery("select e from Produit e where e.id = ?1", Produit.class)
					.setParameter(1, id).getSingleResult());
		}

		catch (Exception ex) {
			return Optional.empty();
		}

		finally {
			em.close();
		}
	}

	public Produit save(Produit entity) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();

		try {
			if (em.contains(entity)) {
				em.persist(entity);
			}

			else { // UPDATE
				entity = em.merge(entity);
			}

			em.getTransaction().commit();
		}

		catch (Exception ex) {
			ex.printStackTrace();
			em.getTransaction().rollback();
		}

		finally {
			em.close();
		}

		return entity;
	}

	public void deleteById(Long id) {
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();

		try {
			em.createQuery("delete from Produit e where e.id = ?1").setParameter(1, id).executeUpdate();
			em.getTransaction().commit();
		}

		catch (Exception ex) {
			ex.printStackTrace();
			em.getTransaction().rollback();
		}

		finally {
			em.close();
		}
	}

	@Override
	public List<Produit> findByPrixBetween(Double a, Double b) {
		EntityManager em = emf.createEntityManager();

		try {
			return em.createQuery("select p from Produit p where p.prixVente between ?1 and ?2", Produit.class)
					.setParameter(1, a).setParameter(2, b).getResultList();
		}

		catch (Exception ex) {
			return new ArrayList<>();
		}

		finally {
			em.close();
		}
	}
	
	public Optional<Produit> findByIdWithCommentaires(Long id) {
		EntityManager em = emf.createEntityManager();

		try {
			return Optional.of(em.createQuery("select e from Produit e left join fetch e.commentaires where e.id = ?1", Produit.class)
					.setParameter(1, id).getSingleResult());
		}

		catch (Exception ex) {
			return Optional.empty();
		}

		finally {
			em.close();
		}
	}
}