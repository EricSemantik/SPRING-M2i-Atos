package tp.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Principal {

	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		
		// Guitare guitare = new Guitare();
		// Guitariste guitariste = new Guitariste(guitare); 
		
		
		IMusicien musicien = (IMusicien) context.getBean("guitariste");
		musicien.jouer();
		
		IMusicien musicienBis = context.getBean(IMusicien.class);
		musicienBis.jouer();
		
		IMusicien musicienTer = context.getBean("guitariste", IMusicien.class);
		musicienTer.jouer();

		
		context.close();
	}

}

