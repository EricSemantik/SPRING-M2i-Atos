package spring.formation.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;

@Entity
@DiscriminatorValue("fournisseur")
@NamedQuery(name = "Fournisseur.findAllWithoutProduits", query = "select f from Fournisseur f where f.produits is empty")
public class Fournisseur extends Personne {
	@Column(name = "FOU_RESPONSABLE", length = 200, nullable = false)
	@JsonView(Views.ViewCommon.class)
	private String responsable;

	@OneToMany(mappedBy = "fournisseur")
	@JsonView(Views.ViewFournisseurWithProduits.class)
	private List<Produit> produits = new ArrayList<>();

	@ManyToMany(mappedBy = "fournisseurs")
	private List<Adresse> adresses = new ArrayList<>();

	public String getResponsable() {
		return responsable;
	}

	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	public List<Adresse> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<Adresse> adresses) {
		this.adresses = adresses;
	}

	@Override
	public String toString() {
		return " > " + this.id + ". " + this.nom;
	}
}
