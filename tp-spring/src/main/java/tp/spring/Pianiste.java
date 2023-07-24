package tp.spring;

public class Pianiste implements IMusicien {

	private String morceau = "Vive le vent";

	private IInstrument instrument = new Piano();

	public Pianiste() {
		super();
	}

	
	public Pianiste(IInstrument instrument) {
		super();
		this.instrument = instrument;
	}

	@Override
	public void jouer() {
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

}
