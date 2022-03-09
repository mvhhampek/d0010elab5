package lab5.general;

import java.util.Observable;
import lab5.store.StoreState;

public class PrintView extends View {
    private State state;
    private StoreState storeState;
    public PrintView(State s){
        state = s;
        storeState = s.getStore();
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub
        
    }

    public void printParameter(){
        System.out.println("PARAMETRAR\n" + 
        "==========\n"+
        "Antal kassor, n.........: " + "nr of checkouts\n"+
        "Max som ryms, m.........: " + "max nr of costumers\n"+
        "Ankomsthastighet, lambda..: " + "siffra\n"+
        "Plocktider, [P_min..P_max]: " + "[pminpmax]\n"+
        "Betaltider, [K_min..K_max]: " + "[kminkmax]\n"+
        "Frö, f...................: " + "frö\n" +
        "FÖRLOPP\n" + "=======\n" + 
        "Tid Händelse  Kund  ?  led    ledT    I     $    :-(   köat    köT   köar  [Kassakö..]" );


    }
    public void printEvents(){

    }
    public void results(){

    }

}
