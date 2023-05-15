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
	 * @param message {@link ScrewDriver} the name
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
			throw new Error(t);
		}
	}
	@Override
	public Thread newThread(Runnable r) {
		return new Thread(r);
	}
}