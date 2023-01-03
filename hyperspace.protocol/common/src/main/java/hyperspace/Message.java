package hyperspace;

public interface Message 
	extends Cloneable, java.io.Serializable {

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
	
	/**
	 * Returns the command.
	 * @return the command.
	 */
	String getCommand();
	
	/**
	 * Sets the command.
	 * @param command {@link String} the command
	 */
	void setCommand(String command);
}