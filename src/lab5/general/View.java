package lab5.general;

import java.util.Observable;
import java.util.Observer;
@SuppressWarnings("deprecated")
public abstract class View implements Observer {



	public abstract update(Observable o, Object arg);



}
