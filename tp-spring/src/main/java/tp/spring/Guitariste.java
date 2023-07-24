package tp.spring;

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
