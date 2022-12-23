package hyperspace;

import hyperspace.recurrent.AbstractSet;
import hyperspace.recurrent.Collection;

public class Listeners extends AbstractSet<Listener> {

	private static final long serialVersionUID = -3506882592585282661L;
	
	public Listeners() {
		super(Listeners.class);
	}
	public Listeners(Listeners parent, Listener element) {
		super(parent, element);
	}
	@Override
	public boolean add(Listener e) {
		if(isEmpty()) {
			setElement(e);
			return true;
		} else if(!contains(e)) {
			instance(getParentClass(), getParent(), e);
			return true;
		} else return false; 
	}
	private static <X> X instance(Class<X> type, Collection<Listener> parent, Listener element) {
		try {
			return type.getDeclaredConstructor(parent.getClass(), Listener.class).newInstance(parent, element);
		}
		catch(Throwable t) {
			throw new Error(t);
		}
	}
}
