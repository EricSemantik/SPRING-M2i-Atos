package spring.formation.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.formation.model.Commande;

public interface ICommandeRepository extends JpaRepository<Commande, Long> {

}
