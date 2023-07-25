package spring.formation.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "reparateur")
public class Reparateur {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "REP_ID")
	private Long id;

	@Column(name = "REP_NOM", length = 100, nullable = false)
	private String nom;

	@Column(name = "REP_TEL", length = 20)
	private String telephone;

	@Column(name = "REP_DESCRIPTION")
	private String description;

	@ManyToMany
	@JoinTable(
		name = "reparation",
		joinColumns = @JoinColumn(name = "REPA_REPARATEUR_ID"),
		inverseJoinColumns = @JoinColumn(name = "REPA_PRODUIT_ID")
	)
	private List<Produit> produitsReparables = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Produit> getProduitsReparables() {
		return produitsReparables;
	}

	public void setProduitsReparables(List<Produit> produitsReparables) {
		this.produitsReparables = produitsReparables;
	}
}
