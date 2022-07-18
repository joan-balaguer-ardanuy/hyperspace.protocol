package hyperspace;

import java.util.EventObject;

/**
 * Event arguments class.
 * Each fired event will have an argument that inherits this class.
 * 
 * @author joan
 *
 */
public class EventArgs extends EventObject {

	/**
	 * 6347247597829991161L
	 */
	private static final long serialVersionUID = 6347247597829991161L;
	
	/**
	 * The command.
	 */
	String command;

	/**
	 * The value.
	 */
	XML message;
	
	/**
	 * Returns the command.
	 * @return the command.
	 */
	public String getCommand() {
		return command;
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
	public EventArgs(Listener source, String command) {
		this(source, source.getMessage(), command);
		this.command = command;
	}
	/**
	 * {@link EventArgs} default class constructor.
	 * @param source {@link Listener} the source of the event
	 * @param message {@link Object} the value of the event.
	 * @param command {@link Command} the command
	 */
	private EventArgs(Listener source, XML message, String command) {
		super(source);
		this.message = message;
		this.command = command;
	}
}