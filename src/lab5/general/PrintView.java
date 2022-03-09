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
        "Antal kassor, n.........: " + storeState.getMaxCheckouts() + "\n"+
        "Max som ryms, m.........: " + storeState.getMaxCustomers() + "\n"+
        "Ankomsthastighet, lambda..: " + storeState.getLambda() + "\n"+
        "Plocktider, [P_min..P_max]: " + "[" + storeState.getMinPickTime() +".." + storeState.getMaxPickTime() +"]\n" + 
        "Betaltider, [K_min..K_max]: " + "["+storeState.getMinPayTime()+".."+storeState.getMaxPayTime()+"]\n"+
        "Frö, f...................:"
        "FÖRLOPP\n" + "=======\n" + 
        "Tid Händelse  Kund  ?  led    ledT    I     $    :-(   köat    köT   köar  [Kassakö..]" );


    }
    public void printEvents(){

    }
    public void results(){

    }

}
