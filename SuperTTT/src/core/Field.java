package core;


public class Field implements ISeeded {
	
	private Seed state;
	
	public Field() {
		this.state = Seed.N;
	}

	@Override
	public Seed getState() {
		return this.state;
	}

	@Override
	public Seed setState(Seed s) {
		Seed old = this.state;
		this.state = s;
		return old;
	}
	
	public boolean isOpen() {
		return this.state == Seed.N;
	}
	
}
