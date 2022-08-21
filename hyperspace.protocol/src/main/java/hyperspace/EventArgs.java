package hyperspace;

import java.util.EventObject;

/**
 * Event arguments class.
 * Each fired event will have an argument that inherits this class.
 * 
 * @author joan
 *
 */
public class EventArgs extends EventObject implements Listener {

	/**
	 * 6347247597829991161L
	 */
	private static final long serialVersionUID = 6347247597829991161L;

	/**
	 * The value.
	 */
	XML message;
	
	/**
	 * Returns the command.
	 * @return the command.
	 */
	public String getCommand() {
		return message.getCommand();
	}
	
	/**
	 * Returns the value of this event.
	 * @return the value of this event.
	 */
	public XML getMessage() {
		return message;
	}
	
	/**
	 * {@link EventArgs} default class constructor.
	 * @param source {@link Listener} the source of the event
	 * @param command {@link Command} the command
	 */
	public EventArgs(Listener source, XML message) {
		super(source);
		this.message = message;
	}
	
	@Override
	public Listener getSource() {
		return (Listener) super.getSource();
	}

	@Override
	public void event(EventArgs e) {
		getSource().event(e);
	}

	@Override
	public void addEventListener(Listener listener) {
		getSource().addEventListener(listener);
	}

	@Override
	public void removeEventListener(Listener listener) {
		getSource().removeEventListener(listener);
	}
}