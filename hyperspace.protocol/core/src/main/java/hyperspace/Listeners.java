package hyperspace;

import hyperspace.recurrent.AbstractSet;

public class Listeners extends AbstractSet<Listener> {

	private static final long serialVersionUID = -3506882592585282661L;
	
	public Listeners() {
		super();
	}
	public Listeners(Listeners parent, Listener entry) {
		super(parent, entry);
	}
	@Override
	public boolean add(Listener e) {
		if(isEmpty()) {
			setEntry(e);
			return true;
		} else if(!contains(e)) {
			instance(getClass(), getParent(), e);
			return true;
		} else return false; 
	}
	private static <X> X instance(Class<X> type, Object parent, Listener entry) {
		try {
			return type.getDeclaredConstructor(parent.getClass(), Listener.class).newInstance(parent, entry);
		} catch(Throwable t) {
			throw new Error(t);
		}
	}
}
