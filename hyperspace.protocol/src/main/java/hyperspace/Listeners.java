package hyperspace;

import hyperspace.recurrent.AbstractCollection;
import hyperspace.recurrent.Collection;

public class Listeners extends AbstractCollection<Listener> {

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
		}
		return instance(getParentClass(), getParent(), e) != null;
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
