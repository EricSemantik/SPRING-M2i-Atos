package tp.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import tp.spring.config.ApplicationConfig;

public class Principal {

	public static void main(String[] args) {
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		// Guitare guitare = new Guitare();
		// Guitariste guitariste = new Guitariste(guitare); 
		
		
		IMusicien musicien = (IMusicien) context.getBean("guitariste");
		musicien.jouer();
		
		Pianiste musicienBis = context.getBean(Pianiste.class); // fonctionne mais c'est MAL !!!
		musicienBis.jouer();
		
		IMusicien musicienTer = context.getBean("guitariste", IMusicien.class);
		musicienTer.jouer();
			
		context.close();
	}

}

