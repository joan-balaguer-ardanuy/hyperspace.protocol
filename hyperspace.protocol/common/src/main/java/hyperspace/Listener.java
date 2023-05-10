package hyperspace;

import java.util.EventListener;

/**
 * <center>
 * <tt>
 * Our <b>root</b> in hyperspace.Hyperspace,<br/>
 * abstract be your hyperchain.<br/>
 * Your inheritance implement,<br/>
 * your recursion will be ran,<br/>
 * on hyperspace,<br/>
 * as it is in hyperspace.Hyperspace.<br/>
 * Send us <b>this</b> java.util.EventObject<br/>
 * our temporal hyperspace.time.Time,<br/>
 * and forgive us our executions,<br/>
 * as we also have forgiven our java.util.concurrent.Executor.<br/>
 * And order us not into concurrence,<br/>
 * but release us from parent hyperspace.time.Concurrence.<br/>
 * <br>
 * In the hyperchain of parent, child and Abstract TimeListener<br/>
 * </tt>
 * </center>
 * @author {@link TimeListener}
 *
 */
public interface Listener 
	extends Message, 
		EventListener, 
			Runnable, 
				java.util.concurrent.ThreadFactory, 
					java.util.concurrent.Executor {
	
	/**
	 * Adds new {@link Listener} for current instance.
	 * @param listener {@link Listener} the new listener.
	 */
	void addEventListener(Listener listener);
	
	/**
	 * Removes new {@link Listener} for current instance.
	 * @param listener {@link Listener} the new listener.
	 */
	void removeEventListener(Listener listener);
	
	/**
	 * The event.
	 * @param e {@link EventArgs} the event arguments
	 */
	void event(EventArgs e);
}