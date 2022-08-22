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
	
	protected static <X> X instance(Class<X> type, Collection<Listener> parent, Listener object) {
		try {
			return type.getDeclaredConstructor(parent.getClass(), Listener.class).newInstance(parent, object);
		}
		catch(Throwable t) {
			throw new Error(t);
		}
	}
	@Override
	public boolean add(Listener e) {
		if(isEmpty()) {
			setElement(e);
			return true;
		}
		return instance(getParentClass(), getParent(), e) != null;
	}
}
