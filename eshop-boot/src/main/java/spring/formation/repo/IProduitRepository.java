package spring.formation.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring.formation.model.Produit;

public interface IProduitRepository extends JpaRepository<Produit, Long> {
	public List<Produit> findByPrixVenteBetween(Double a, Double b); // par convention de nommage

	@Query("select p from Produit p left join fetch p.commentaires where p.id = ?1")
	public Optional<Produit> findByIdWithCommentaires(Long id); // par @Query
	
	public List<Produit> findAllByStock(@Param("quantite") int stock); // par @NamedQuery dans l'entité Produit
	

}
