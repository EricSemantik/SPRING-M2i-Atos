package spring.formation.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.formation.model.Fournisseur;

public interface IFournisseurRepository extends JpaRepository<Fournisseur, Long> {
	// Rechercher la liste des fournisseurs commençant par un nom donné => convention de nommage
	
	// Rechercher la liste des fournisseurs qui sont dans une ville donné => @Query
	
	// Rechercher la liste des fournisseurs qui n'ont aucun produit référencé => par @NamedQuery
}
