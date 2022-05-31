package hyperspace;

import java.util.Arrays;
import java.util.Random;

public class Program 
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
	@Override
	public Object clone() {
		return null;
	}
	
	/**
	 * The randomness.
	 */
	transient Random random;
	
	/**
	 * The random.
	 * @return the random.
	 */
	protected Random random() {
		return random == null ? (random = new Random()) : random;
	}
}