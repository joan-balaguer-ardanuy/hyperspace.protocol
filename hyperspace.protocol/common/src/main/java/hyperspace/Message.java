package hyperspace;

public interface Message 
	extends Cloneable, java.io.Serializable {

	/**
	 * Returns the name of this instance.
	 * @return the name of this instance.
	 */
	XML getMessage();
	/**
	 * Sets the name of this instance.
	 * @param message {@link String} the name of this instance.
	 */
	void setMessage(XML message);
}