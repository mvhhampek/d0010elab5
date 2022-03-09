package lab5.general;

import java.util.Observable;
import java.util.Observer;
@SuppressWarnings("deprecation")
public abstract class View implements Observer {



	public abstract void update(Observable o, Object arg);



}
