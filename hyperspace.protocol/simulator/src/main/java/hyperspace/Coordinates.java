package hyperspace;

import hyperspace.recurrent.AbstractSet;

public class Coordinates extends AbstractSet<Coordinate> {

	private static final long serialVersionUID = 8410147537146805214L;
	
	public Coordinates() {
		super();
	}
	public Coordinates(Coordinates parent, Coordinate entry) {
		super(parent, entry);
	}
	
	@Override
	public boolean add(Coordinate e) {
		if(isEmpty()) {
			setEntry(e);
			return true;
		} else if(!contains(e)) {
			instance(getClass(), getParent(), e);
			return true;
		} else return false; 
	}
}
