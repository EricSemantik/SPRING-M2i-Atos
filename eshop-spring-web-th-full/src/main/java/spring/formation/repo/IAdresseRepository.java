package spring.formation.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.formation.model.Adresse;

public interface IAdresseRepository extends JpaRepository<Adresse, Long> {

}
