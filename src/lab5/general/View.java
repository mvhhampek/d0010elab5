package lab5.general;

import java.util.Observable;
import java.util.Observer;

/**
 * General View
 * 
 * @author Hampus Kämppi, Gustav Edner, Jonathan Junel, Linus Karlsson
 *
 */
@SuppressWarnings("deprecation")
public abstract class View implements Observer {
	/**
	 * Method called when observers are notified
	 */
	public abstract void update(Observable o, Object arg);
}
