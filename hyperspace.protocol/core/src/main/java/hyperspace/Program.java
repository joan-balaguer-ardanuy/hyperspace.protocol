package hyperspace;

import java.util.Arrays;

public abstract class Program 
	extends XML 
		implements Listener {

	/**
	 * -6123701282190160441L
	 */
	private static final long serialVersionUID = -6123701282190160441L;

	/**
	 * The maximum size of array to allocate.
	 * Some VMs reserve some header words in an array.
	 * Attempts to allocate larger arrays may result in
	 * OutOfMemoryError: Requested array size exceeds VM limit
	 */
	protected static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

	/**
	 * The command.
	 */
	private String command;
	
	/**
	 * The event listeners array.
	 */
	private Listener[] eventListeners;

	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
		sendEvent(new EventArgs(this, command));
	}
	
	/**
	 * {@link Program} default class constructor.
	 */
	public Program() {
		super();
	}
	/**
	 * {@link Program} class constructor.
	 * @param name {@link String} the name
	 */
	public Program(String name) {
		super(name);
	}
	
	public final void addEventListener(Listener listener) {
		if(eventListeners == null) {
			eventListeners = new Listener[] { listener };
			return;
		}
		else if(listener == null) {
			return;
		}
		else {
			eventListeners = Arrays.copyOf(eventListeners, eventListeners.length+1);
			eventListeners[eventListeners.length-1] = listener;	
		}
	}
	public final void removeEventListener(Listener listener) {
		if(eventListeners == null) {
			return;
		}
		else if (listener == null) {
			return;
        }
		else for (int index = 0; index < eventListeners.length; index++) {
            if (listener == eventListeners[index]) {
                int numMoved = eventListeners.length - index - 1;
                if (numMoved > 0) {
                    System.arraycopy(eventListeners, index + 1, eventListeners, index, numMoved);
                }
                eventListeners[eventListeners.length-1] = null;
            }
		}
	}
	/**
	 * Sends event to all event {@link Listener} added in the set.
	 * @param e {@link EventArgs} the arguments of the event
	 */
	protected void sendEvent(EventArgs e) {
		for(Listener listener : eventListeners) {
			// send event to all event listeners
			listener.event(e);
		}
	}
	@Override
	public void event(EventArgs e) {
		sendEvent(e);
	}
}