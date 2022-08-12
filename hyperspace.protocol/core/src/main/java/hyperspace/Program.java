package hyperspace;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
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
	 * XML unmarshall method. Generates new {@link JAXBContext} for current class,
	 * instances new {@link Unmarshaller} object and unmarshalls the {@link InputStream} argument.
	 * @param inputStream {@link InputStream} the input stream to be unmarshalled.
	 * @throws JAXBException thrown when something is wrong
	 */
	@SuppressWarnings("unchecked")
	public static <T extends XML> T read(Class<T> type, InputStream inputStream) throws JAXBException {
		try {
			JAXBContext context = JAXBContext.newInstance(type);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			return (T) unmarshaller.unmarshal(inputStream);
		} catch (Exception e) {
			throw new JAXBException(e.getMessage(), e.getCause());
		}
	}
	/**
	 * XML marshall method. Generates new {@link JAXBContext} for current class,
	 * instances new {@link Marshaller} object and marshalls the {@link OutputStream} argument.
	 * @param outputStrem {@link OutputStream} the output stream to be marshalled.
	 * @throws JAXBException when something is wrong
	 */
	public final void write(OutputStream outputStrem) throws JAXBException {
		try {
			JAXBContext context = JAXBContext.newInstance(getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(this, outputStrem);
		} catch (JAXBException e) {
			throw new JAXBException(e.getMessage(), e.getCause());
		}
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		try {
			// instances new JAXBContext for current class
			JAXBContext context = JAXBContext.newInstance(getClass());
			// create marshaller
			Marshaller marshaller = context.createMarshaller();
			// output pretty printed
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			// instances new StringWriter
			StringWriter sw = new StringWriter();
			// marshall XML message into StringWriter
			marshaller.marshal(this, sw);
			// get the XML message as string
			String strXml = sw.toString();
			// return string XML message
			return strXml;
		} 
		catch (JAXBException e) {
			// if something is wrong print stack trace
			e.printStackTrace();
			return null;
		}
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