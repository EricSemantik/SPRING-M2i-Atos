package tp.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Principal {
	
	@Autowired
	@Qualifier("guitariste")
	private IMusicien musicien;
	
	@Autowired
	private Pianiste musicienBis;

	public void run() {
		musicien.jouer();
		
		musicienBis.jouer();
	}

}

