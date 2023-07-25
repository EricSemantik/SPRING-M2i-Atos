package spring.formation;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.formation.model.Fournisseur;
import spring.formation.repo.IFournisseurRepository;

public class TestSpringXml {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

		IFournisseurRepository fournisseurRepo = context.getBean(IFournisseurRepository.class);

		Fournisseur fournisseur = new Fournisseur();

		fournisseur.setNom("F1");
		fournisseur.setResponsable("RESP");

		fournisseur = fournisseurRepo.save(fournisseur);

		context.close();
	}

}
