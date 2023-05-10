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
	
	@Override
	public String getCommand() {
		return getSource().getCommand();
	}
	@Override
	public void setCommand(String command) {
		getSource().setCommand(command);
	}
	@Override
	public Parity getParity() {
		return getSource().getParity();
	}
	@Override
	public void setParity(Parity parity) {
		getSource().setParity(parity);
	}
	@Override
	public String getName() {
		return getSource().getName();
	}
	
	/**
	 * {@link EventArgs} default class constructor.
	 * @param source {@link Listener} the source of the event
	 * @param message {@link Message} the message
	 */
	public EventArgs(Listener source) {
		super(source);
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
	@Override
	public Thread newThread(Runnable r) {
		return getSource().newThread(r);
	}
	@Override
	public void execute(Runnable command) {
		getSource().execute(command);
	}
}