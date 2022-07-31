package hyperspace;

import java.util.Arrays;

import jakarta.xml.bind.annotation.XmlElement;

public abstract class Program 
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
	
	private XML message;
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
	@XmlElement
	public XML getMessage() {
		return message;
	}
	public void setMessage(XML message) {
		this.message = message;
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
	 * {@link Program} default class constructor.
	 */
	public Program() {
		super();
	}
	/**
	 * {@link Program} class constructor.
	 * @param message {@link String} the name
	 */
	public Program(XML message) {
		super();
		this.message = message;
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
	/**
	 * Intances new object.
	 * @param <X> the parameter type of the returned object
	 * @param type the {@link Class} of the object.
	 * @param object the arguments of the construction of the object
	 * @return the new <X> instance
	 */
	protected static <X> X instance(Class<X> type, Object object) {
		try {
			return type.getDeclaredConstructor(object.getClass()).newInstance(object);
		}
		catch(Throwable t) {
			throw new Error(t);
		}
	}
	protected static <X> X instance(Class<X> type, Object parent, Object object) {
		try {
			return type.getDeclaredConstructor(parent.getClass().getSuperclass(), object.getClass()).newInstance(parent, object);
		}
		catch(Throwable t) {
			throw new Error(t);
		}
	}
	protected static <X> X instance(Class<X> type, Object root, Object stem, Object object) {
		try {
			return type.getDeclaredConstructor(root.getClass().getSuperclass(), stem.getClass().getSuperclass(), object.getClass()).newInstance(root, stem, object);
		}
		catch(Throwable t) {
			throw new Error(t);
		}
	}
	protected static <X> X instance(Class<X> type, Object childClass, Object parent, Object object, Object element) {
		try {
			return type.getDeclaredConstructor(childClass.getClass(), parent.getClass().getSuperclass(), object.getClass(), element.getClass()).newInstance(childClass, parent, object, element);
		}
		catch(Throwable t) {
			throw new Error(t);
		}
	}
	protected static <X> X instance(Class<X> type, Object childClass, Object parent, Object object, Object key, Object value) {
		try {
			return type.getDeclaredConstructor(childClass.getClass(), parent.getClass().getSuperclass(), object.getClass(), key.getClass(), value.getClass()).newInstance(childClass, parent, object, key, value);
		}
		catch(Throwable t) {
			throw new Error(t);
		}
	}
}