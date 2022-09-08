package hyperspace;

import jakarta.xml.bind.annotation.XmlElement;

public class Language implements Message {

	private static final long serialVersionUID = 3883677905936515475L;
	/**
	 * The name.
	 */
	private XML2<?,?> message;
	
	@XmlElement
	public XML2<?, ?> getMessage() {
		return message;
	}
	public void setMessage(XML2<?, ?> message) {
		this.message = message;
	}
	@XmlElement
	public String getName() {
		return message.getName();
	}
	public void setName(String name) {
		message.setName(name);;
	}
	@Override
	public String getCommand() {
		return message.getCommand();
	}
	@Override
	public void setCommand(String command) {
		message.setCommand(command);;
	}
	
	public Language() {
		super();
	}
	public Language(XML2<?,?> message) {
		super();
		this.message = message;
	}
	@Override
	public Message clone() {
		try {
			return (Message) super.clone();
		} catch (CloneNotSupportedException e) {
			return null;
		}
	}
	@Override
	public String toString() {
		return XML.toString(this);
	}
}
