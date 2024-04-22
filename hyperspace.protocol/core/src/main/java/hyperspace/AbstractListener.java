package hyperspace;

import hyperspace.recurrent.Set;

import java.util.Iterator;

import javax.xml.bind.annotation.XmlTransient;

public abstract class AbstractListener
	extends XML
		implements Listener {
	
	private static final long serialVersionUID = -6531537504810067678L;
	
	/**
	 * The listeners
	 */
	private Set<Listener> listeners;
	
	@Override
	@XmlTransient
	public String getCommand() {
		return super.getCommand();
	}
	@Override
	public void setCommand(String command) {
		super.setCommand(command);
		sendEvent(new EventArgs(this));
	}
	
	/**
	 * {@link AbstractListener} default class constructor
	 */
	public AbstractListener() {
		super();
	}
	/**
	 * {@link AbstractListener} class constructor.
	 * @param message {@link Parity} the parity
	 */
	public AbstractListener(Parity parity) {
		super(parity);
	}
	
	@Override
	public void addEventListener(Listener listener) {
		if(listeners == null) {
			listeners = new Listeners();
		}
		listeners.add(listener);
	}
	@Override
	public void removeEventListener(Listener listener) {
		if(listeners == null) {
			return;
		}
		listeners.remove(listener);
	}
	/**
	 * Sends event to all event {@link Listener} added in the set.
	 * @param e {@link EventArgs} the arguments of the event
	 */
	protected void sendEvent(EventArgs e) {
		if(listeners != null) {
			Iterator<Listener> iterator = listeners.iterator();
			while(iterator.hasNext()) {
				iterator.next().event(e);
			}
		}
	}
	@Override
	public void event(EventArgs e) {
		sendEvent(e);
	}
	@Override
	public void run() {
		switch (getCommand()) {
		case Command.LISTEN:
			setCommand(Command.TRANSFER);
			break;
		default:
			setCommand(Command.LISTEN);
			break;
		}
	}
	@Override
	public void execute(Runnable command) {
		try {
			newThread(command).start();
		}
		catch (Throwable t) {
			return;
		}
	}
	@Override
	public Thread newThread(Runnable r) {
		return new Thread(r);
	}
	/**
	 * Intances new object.
	 * @param <X> the parameter type of the returned object
	 * @param type the {@link Class} of the object.
	 * @param object the arguments of the construction of the object
	 * @return the new <X> instance
	 */
	protected static <X> X instance(Class<X> type, Object... objects) {
		try {
			return type.getDeclaredConstructor(getClasses(objects)).newInstance(objects);
		}
		catch(Throwable t) {
			throw new Error(t);
		}
	}
	
	private static Class<?>[] getClasses(Object... objects) {
		Class<?>[] classes = new Class<?>[objects.length];
		for(int i = 0; i < objects.length; i++) {
			classes[i] = objects[i].getClass();
		}
		return classes;
	}
}