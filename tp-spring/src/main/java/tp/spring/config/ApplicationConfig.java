package tp.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import tp.spring.Guitare;
import tp.spring.Guitariste;
import tp.spring.IInstrument;
import tp.spring.IMusicien;
import tp.spring.Ukulele;

//@Configuration
@ComponentScan("tp.spring")
public class ApplicationConfig {
	
	@Value("Frère Jacques")
	private String morceau;
	
	@Bean
	public IInstrument guitare() {
		return new Guitare();
	}
	
	@Bean
	public IInstrument ukulele() {
		return new Ukulele();
	}
	
	@Bean
	public IMusicien guitariste(Ukulele ukulele) {
		return new Guitariste(this.morceau, ukulele);
	}
}
