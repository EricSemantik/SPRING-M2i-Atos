package tp.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Guitariste implements IMusicien {

	private String morceau = "Vive le vent";
	private IInstrument instrument;

	public Guitariste() {
		super();
	}

	
	
	public Guitariste(IInstrument instrument) {
		super();
		this.instrument = instrument;
	}
	
	
	@Autowired
	public Guitariste(@Value("Frère Jacques") String morceau, @Qualifier("ukulele") IInstrument instrument) {
		super();
		this.morceau = morceau;
		this.instrument = instrument;
	}


	@Override
	public void jouer() {
		System.out.println("Le guitariste joue " + this.morceau + " : " + instrument.toString());
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
		System.out.println("setInstrument");
		this.instrument = instrument;
		
	}


}
