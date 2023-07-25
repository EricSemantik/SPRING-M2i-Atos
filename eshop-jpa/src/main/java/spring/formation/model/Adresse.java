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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Entity
@Table(name = "adresse")
public class Adresse {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ADR_ID")
	private Long id;

	@Column(name = "ADR_RUE", length = 200, nullable = false)
	private String rue;

	@Column(name = "ADR_VILLE", length = 150)
	private String ville;

	@Column(name = "ADR_CP", length = 10)
	private String codePostal;

	@ManyToOne
	@JoinColumn(name = "ADR_CLIENT_ID")
	private Client client;
	
	@ManyToMany
	@JoinTable(
		name = "adresses_fournisseur",
		joinColumns = @JoinColumn(name = "ADRF_ADRESSE_ID"),
		inverseJoinColumns = @JoinColumn(name = "ADRF_FOURNISSEUR_ID")
	)
	private List<Fournisseur> fournisseurs = new ArrayList<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Fournisseur> getFournisseurs() {
		return fournisseurs;
	}

	public void setFournisseurs(List<Fournisseur> fournisseurs) {
		this.fournisseurs = fournisseurs;
	}
}
