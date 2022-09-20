package hyperspace;

import java.util.EventObject;

/**
 * Event arguments class.
 * Each fired event will have an argument that inherits this class.
 * 
 * @author joan
 *
 */
public class EventArgs<K,V> extends EventObject implements TimeListener<K,V> {

	/**
	 * 6347247597829991161L
	 */
	private static final long serialVersionUID = 6347247597829991161L;

	/**
	 * The message.
	 */
	XML message;
	
	@Override
	public XML getMessage() {
		return message;
	}
	@Override
	public void setMessage(XML message) {
		this.message = message;
	}
	@Override
	public String getCommand() {
		return getSource().getCommand();
	}
	@Override
	public void setCommand(String command) {
		getSource().setCommand(command);
	}
	@Override
	public String getName() {
		return getSource().getName();
	}
	@Override
	public void setName(String name) {
		getSource().setName(name);
	}
	
	/**
	 * {@link EventArgs} default class constructor.
	 * @param source {@link Listener} the source of the event
	 * @param message {@link Message} the message
	 */
	public EventArgs(TimeListener<K,V> source, XML message) {
		super(source);
		this.message = message;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public TimeListener<K,V> getSource() {
		return (TimeListener<K,V>) super.getSource();
	}
	@Override
	public void run() {
		getSource().run();
	}
	@Override
	public void addEventListener(Listener listener) {
		getSource().addEventListener(listener);
	}
	@Override
	public void removeEventListener(Listener listener) {
		getSource().removeEventListener(listener);
	}
	@Override
	public void event(EventArgs<?,?> e) {
		getSource().event(e);
	}
	@Override
	public K getParent() {
		return getSource().getParent();
	}
	@Override
	public void setParent(K key) {
		getSource().setParent(key);
	}
	@Override
	public V getChild() {
		return getSource().getChild();
	}
	@Override
	public void setChild(V value) {
		getSource().setChild(value);
	}
}