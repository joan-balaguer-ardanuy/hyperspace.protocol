package hyperspace;

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

	/**
	 * Sends event to all event {@link Listener} added in the set.
	 * @param e {@link EventArgs} the arguments of the event
	 */
	protected abstract void sendEvent(EventArgs e);
	
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
	protected static <X> X instance(Class<X> type, Object... objects) {
		try {
			return type.getDeclaredConstructor(getClasses(objects)).newInstance(objects);
		}
		catch(Throwable t) {
			throw new Error(t);
		}
	}
	private static Class<?>[] getClasses(Object... objects) {
		Class<?>[] classes = new Class<?>[objects.length];
		for(int i = 0; i < objects.length; i++) {
			classes[i] = objects[i].getClass();
		}
		return classes;
	}
}