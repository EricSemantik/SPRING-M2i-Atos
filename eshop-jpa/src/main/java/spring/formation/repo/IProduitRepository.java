package spring.formation.repo;

import java.util.List;

import spring.formation.model.Produit;

public interface IProduitRepository extends IRepository<Produit> {
	public List<Produit> findByPrixBetween(Double a, Double b);
}
