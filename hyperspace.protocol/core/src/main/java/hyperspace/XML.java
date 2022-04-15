/**
 * 
 */
package hyperspace;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Random;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author joan
 *
 */
public abstract class XML implements Listener {

	/**
	 * 7585153185633646322L
	 */
	private static final long serialVersionUID = 7585153185633646322L;

	/**
	 * The maximum size of array to allocate.
	 * Some VMs reserve some header words in an array.
	 * Attempts to allocate larger arrays may result in
	 * OutOfMemoryError: Requested array size exceeds VM limit
	 */
	protected static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;
	
	/**
	 * The event listeners array.
	 */
	private Listener[] eventListeners;

	/**
	 * The command.
	 */
	private String command;

	/**
	 * The name.
	 */
	private String name;

	@Override
	@XmlAttribute
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name  = name;
	}
	@XmlElement
	public String getCommand() {
		return command;
	}
	public void setCommand(String command) {
		this.command = command;
	}

	/**
	 * {@link XML} default class constructor.
	 */
	public XML() {
		super();
	}

	/**
	 * {@link XML} class constructor.
	 * @param name {@link String} the name
	 */
	public XML(String name) {
		super();
		this.name = name;
	}

	@Override
	public abstract Object clone();
	
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
	 * Writes the string XML message into a file.
	 * @param path {@link String} the destination path of the string XML message.
	 */
	public void toFile(String path) {
		// parse XML message into string.
		String str = toString();
		// declare new buffered writer
	    BufferedWriter writer;
		try {
			// instances buffered writer with new file writer with destination path as argument
			writer = new BufferedWriter(new FileWriter(path));
			// write the string to the file
		    writer.write(str);
		    // close writer
		    writer.close();
		} catch (IOException e) {
			// if something is wrong print stack trace
			e.printStackTrace();
		}
	}
	/**
	 * Intances new object.
	 * @param <X> the parameter type of the returned object
	 * @param type the {@link Class} of the object.
	 * @param args the arguments of the construction of the object
	 * @return the new <X> instance
	 */
	protected static <X> X instance(Class<X> type, Object... args) {
		try {
			return type.getDeclaredConstructor(getClasses(args)).newInstance(args);
		}
		catch(Throwable t) {
			throw new Error(t);
		}
	}
	/**
	 * Returns an array of the classes of the object array argument.
	 * @param objects the array of the objects t
	 * @return
	 */
	static Class<?>[] getClasses(Object... objects) {
		Class<?>[] types = new Class<?>[objects.length];
		for(int i = 0; i < objects.length; i++) {
			types[i] = objects[i].getClass();
		}
		return types;
	}
}
