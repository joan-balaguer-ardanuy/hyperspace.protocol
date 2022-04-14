package hyperspace;

public interface Message 
	extends java.io.Serializable {

	/**
	 * Returns the name of this instance.
	 * @return the name of this instance.
	 */
	String getName();
	/**
	 * Sets the name of this instance.
	 * @param {@link String} the name of this instance.
	 */
	void setName(String name);
}