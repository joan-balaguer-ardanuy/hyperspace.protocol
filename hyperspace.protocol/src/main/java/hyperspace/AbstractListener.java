package hyperspace;

import hyperspace.recurrent.Collection;

import javax.xml.bind.annotation.XmlElement;

public abstract class AbstractListener
	implements Listener {
	
	private static final long serialVersionUID = -6531537504810067678L;

	/**
	 * The name.
	 */
	private String name;
	/**
	 * The command.
	 */
	private String command;
	/**
	 * The listeners
	 */
	private Collection<Listener> listeners;
	/**
	 * The message
	 */
	private XML message;

	@Override
	@XmlElement
	public XML getMessage() {
		return message;
	}
	public void setMessage(XML message) {
		this.message = message;
	}
	@Override
	@XmlElement
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	@XmlElement
	public String getCommand() {
		return command;
	}
	@Override
	public void setCommand(String command) {
		this.command = command;
	}

	/**
	 * {@link AbstractListener} default class constructor
	 */
	public AbstractListener() {
		super();
	}
	/**
	 * {@link AbstractListener} class constructor.
	 * @param message {@link XML} the name
	 */
	public AbstractListener(XML message) {
		super();
		this.message = message;
		this.name = message.getName();
		this.command = message.getCommand();
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
	protected void sendEvent(EventArgs<?,?> e) {
		if(listeners != null) {
			for(Listener listener : listeners) {
				listener.event(e);
			}
		}
	}
	@Override
	public void event(EventArgs<?,?> e) {
		sendEvent(e);
	}
	@Override
	public void run() {
		setCommand(Command.TRANSFER);
	}
	@Override
	public Listener clone() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String toString() {
		return XML.toString(this);
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