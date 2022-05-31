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

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

/**
 * @author joan
 *
 */
public abstract class XML implements Message {

	/**
	 * 7585153185633646322L
	 */
	private static final long serialVersionUID = 7585153185633646322L;

	/**
	 * The name.
	 */
	private String name;

	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name  = name;
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