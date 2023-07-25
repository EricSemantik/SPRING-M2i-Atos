package spring.formation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.formation.config.ApplicationConfig;
import spring.formation.model.Fournisseur;
import spring.formation.repo.IFournisseurRepository;

public class TestSpringJava {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		IFournisseurRepository fournisseurRepo = context.getBean(IFournisseurRepository.class);

		Fournisseur fournisseur = new Fournisseur();

		fournisseur.setNom("F1");
		fournisseur.setResponsable("RESP");

		fournisseur = fournisseurRepo.save(fournisseur);
		
		context.close();
	}

}
