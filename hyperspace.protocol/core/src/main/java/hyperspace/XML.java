package hyperspace;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlTransient;

public abstract class XML implements Message {

	private static final long serialVersionUID = -4223002030112388964L;

	/**
	 * The command.
	 */
	private String command;
	
	/**
	 * The parity
	 */
	private Parity parity;


	@Override
	public abstract String getName();
	
	@Override
	@XmlTransient
	public String getCommand() {
		return command;
	}
	@Override
	public void setCommand(String command) {
		this.command = command;
	}
	@Override
	@XmlTransient
	public Parity getParity() {
		return parity;
	}
	public void setParity(Parity parity) {
		this.parity = parity;
	}
	
	public XML() {
		this.command = Command.INSTANCE;
		this.parity = Parity.YY;
	}
	public XML(Parity parity) {
		this.command = Command.INSTANCE;
		this.parity = parity;
	}
	@Override
	public String toString() {
		return toString(this);
	}
	/**
	 * XML unmarshall method. Generates new {@link JAXBContext} for current class,
	 * instances new {@link Unmarshaller} object and unmarshalls the {@link InputStream} argument.
	 * @param inputStream {@link InputStream} the input stream to be unmarshalled.
	 * @throws JAXBException thrown when something is wrong
	 */
	@SuppressWarnings("unchecked")
	public static <T> T read(Class<T> type, InputStream inputStream) throws JAXBException {
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
	public final void write(Object object, OutputStream outputStrem) throws JAXBException {
		try {
			JAXBContext context = JAXBContext.newInstance(object.getClass());
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			marshaller.marshal(object, outputStrem);
		} catch (JAXBException e) {
			throw new JAXBException(e.getMessage(), e.getCause());
		}
	}
	public static String toString(Object object) {
		try {
			// instances new JAXBContext for current class
			JAXBContext context = JAXBContext.newInstance(object.getClass());
			// create marshaller
			Marshaller marshaller = context.createMarshaller();
			
			// output pretty printed
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
			// instances new StringWriter
			StringWriter sw = new StringWriter();
			// marshall XML message into StringWriter
			marshaller.marshal(object, sw);
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
	public static void toFile(Object object, String path) {
		// parse XML message into string.
		String str = toString(object);
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
}
