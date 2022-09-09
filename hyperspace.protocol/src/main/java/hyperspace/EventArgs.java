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
	 * The message.
	 */
	XML message;
	
	@Override
	public XML getMessage() {
		return message;
	}
	@Override
	public void setMessage(XML message) {
		this.message = message;
	}
	@Override
	public String getCommand() {
		return message.getCommand();
	}
	@Override
	public void setCommand(String command) {
		message.setCommand(command);
	}
	@Override
	public String getName() {
		return message.getName();
	}
	@Override
	public void setName(String name) {
		message.setName(name);
	}
	
	/**
	 * {@link EventArgs} default class constructor.
	 * @param source {@link Listener} the source of the event
	 * @param message {@link Message} the message
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
	public void run() {
		getSource().run();
	}
	@Override
	public void addEventListener(Listener listener) {
		getSource().addEventListener(listener);
	}
	@Override
	public void removeEventListener(Listener listener) {
		getSource().removeEventListener(listener);
	}
	@Override
	public void event(EventArgs e) {
		getSource().event(e);
	}
}