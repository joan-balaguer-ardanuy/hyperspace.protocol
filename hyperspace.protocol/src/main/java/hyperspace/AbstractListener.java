package hyperspace;

import hyperspace.recurrent.Collection;

import jakarta.xml.bind.annotation.XmlElement;

public abstract class AbstractListener
	extends XML
	implements Listener {
	
	private static final long serialVersionUID = -6531537504810067678L;
	
	private Collection<Listener> listeners;
	
	private XML message;

	@XmlElement
	public XML getMessage() {
		return message;
	}
	public void setMessage(XML message) {
		this.message = message;
	}
	
	@Override
	@XmlElement
	public String getCommand() {
		return super.getCommand();
	}
	@Override
	public void setCommand(String command) {
		super.setCommand(command);
		event(new EventArgs(this, getMessage()));
	}

	/**
	 * {@link AbstractListener} default class constructor
	 */
	public AbstractListener() {
		super();
	}
	/**
	 * {@link AbstractListener} class constructor.
	 * @param message {@link String} the name
	 */
	public AbstractListener(String name) {
		super(name);
	}
	public AbstractListener(XML message) {
		super(message);
		this.message = message;
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
}