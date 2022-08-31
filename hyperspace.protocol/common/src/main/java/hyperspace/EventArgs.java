package hyperspace;

import java.util.EventObject;

/**
 * Event arguments class.
 * Each fired event will have an argument that inherits this class.
 * 
 * @author joan
 *
 */
public class EventArgs extends EventObject implements Message {

	/**
	 * 6347247597829991161L
	 */
	private static final long serialVersionUID = 6347247597829991161L;

	/**
	 * The value.
	 */
	Message xml;
	
	/**
	 * Returns the XML of this event.
	 * @return the XML of this event.
	 */
	public Message getMessage() {
		return xml;
	}
	@Override
	public String getCommand() {
		return getSource().getCommand();
	}
	@Override
	public void setCommand(String command) {
		getSource().setCommand(command);
	}
	@Override
	public String getName() {
		return getSource().getName();
	}
	@Override
	public void setName(String name) {
		getSource().setName(name);
	}
	
	/**
	 * {@link EventArgs} default class constructor.
	 * @param source {@link Listener} the source of the event
	 * @param xml {@link Message} the xml
	 */
	public EventArgs(Listener source, Message xml) {
		super(source);
		this.xml = xml;
	}
	
	@Override
	public Listener getSource() {
		return (Listener) super.getSource();
	}


}