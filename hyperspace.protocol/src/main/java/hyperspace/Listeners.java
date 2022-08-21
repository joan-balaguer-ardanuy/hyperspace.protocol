package hyperspace;

import hyperspace.recurrent.AbstractCollection;

public class Listeners extends AbstractCollection<Listener> {

	public Listeners() {
		super(Listeners.class);
	}
	public Listeners(Listeners parent, Listener element) {
		super(parent, element);
	}
}
