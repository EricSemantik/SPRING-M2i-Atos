package spring.formation;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.formation.model.Fournisseur;
import spring.formation.model.Produit;
import spring.formation.repo.IFournisseurRepository;
import spring.formation.repo.IProduitRepository;

public class TestSpringXml {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

		IFournisseurRepository fournisseurRepo = context.getBean(IFournisseurRepository.class);
		IProduitRepository produitRepo = context.getBean(IProduitRepository.class);

		Fournisseur fournisseur = new Fournisseur();

		fournisseur.setNom("F1");
		fournisseur.setResponsable("RESP");

		fournisseur = fournisseurRepo.save(fournisseur);
		
		Produit produit = new Produit("NEW");
		
		produit.setPrixAchat(10d);
		produit.setPrixVente(100d);
		produit.setFournisseur(new Fournisseur());
		produit.getFournisseur().setId(1L);
		produit.setModele("MOD");
		produit.setReference("REF");
		
		produit = produitRepo.save(produit);

		context.close();
	}

}
