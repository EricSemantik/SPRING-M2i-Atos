package tp.spring;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Pianiste implements IMusicien {

	private String morceau = "Vive le vent";

	@Autowired
	@Qualifier("piano")
	private IInstrument instrument;

	public Pianiste() {
		super();
	}

	public Pianiste(IInstrument instrument) {
		super();
		this.instrument = instrument;
	}

	@Override
	public void jouer() {
		if(true)
			throw new RuntimeException("Fausse note");
		System.out.println("Le pianiste joue " + this.morceau + " : " + instrument.toString());
	}

	public String getMorceau() {
		return morceau;
	}

	public void setMorceau(String morceau) {
		this.morceau = morceau;
	}

	public IInstrument getInstrument() {
		return instrument;
	}

	public void setInstrument(IInstrument instrument) {
		this.instrument = instrument;
	}
	
	@PostConstruct
	public void commeVousVoulez() {
		System.out.println("Pianiste : Post Construct Appel");
	}

}
