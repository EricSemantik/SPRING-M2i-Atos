package tp.spring;

import tp.spring.aspect.Silence;

public class Guitare implements IInstrument {

	@Override
	@Silence
	public String toString() {
		return "GLINK GLINK GLINK";
	}

}
