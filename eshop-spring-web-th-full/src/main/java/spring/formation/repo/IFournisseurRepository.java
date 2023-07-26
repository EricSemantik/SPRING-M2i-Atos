package spring.formation.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring.formation.model.Fournisseur;

public interface IFournisseurRepository extends JpaRepository<Fournisseur, Long> {
	// Rechercher la liste des fournisseurs commençant par un nom donné => convention de nommage
	List<Fournisseur> findByNomStartingWith(String nom);
	
	// Rechercher la liste des fournisseurs qui sont dans une ville donné => @Query
	@Query("select f from Fournisseur f join f.adresses adr where adr.ville = :ville")
	List<Fournisseur> findAllByVille(@Param("ville") String ville);
	
	// Rechercher la liste des fournisseurs qui n'ont aucun produit référencé => par @NamedQuery
	List<Fournisseur> findAllWithoutProduits(); // à tester rapidement
}
