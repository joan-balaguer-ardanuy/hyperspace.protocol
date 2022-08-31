package hyperspace;

import hyperspace.recurrent.Collection;
import jakarta.xml.bind.annotation.XmlTransient;

public abstract class Time 
	extends XML
		implements Listener {
	
	private static final long serialVersionUID = -6531537504810067678L;
	
	private Collection<Listener> listeners;
	
	private Message xml;
	
	@XmlTransient
	public Message getXML() {
		return xml;
	}
	@Override
	public void setXML(Message xml) {
		this.xml = xml;	
	}
	
	@Override
	public String getCommand() {
		return super.getCommand();
	}
	@Override
	public void setCommand(String command) {
		super.setCommand(command);
		event(new EventArgs(this, getXML()));
	}
	
	/**
	 * {@link Time} class constructor.
	 * @param xml {@link String} the name
	 */
	public Time() {
		super();
	}
	public Time(Message xml) {
		super(xml.getName());
		this.xml = xml;
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
			for(Listener listener : listeners) {
				listener.event(e);
			}
		}
	}
	
	@Override
	public void event(EventArgs e) {
		sendEvent(e);
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

	@Override
	public void run() {
		setCommand(Command.TRANSFER);
	}

	@Override
	public Listener clone() {
		// TODO Auto-generated method stub
		return null;
	}
}