package hyperspace;

public interface Message 
	extends Listener, Cloneable, Runnable, java.io.Serializable {

	/**
	 * Returns the command.
	 * @return the command.
	 */
	String getCommand();
	
	/**
	 * Sets the command.
	 */
	void setCommand(String command);	

	/**
	 * Returns the name of this instance.
	 * @return the name of this instance.
	 */
	String getName();
	
	/**
	 * Sets the name of this instance.
	 * @param name {@link String} the name of this instance.
	 */
	void setName(String name);
}