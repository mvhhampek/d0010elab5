package lab5.general;

import java.util.Observable;
import lab5.store.StoreState;

@SuppressWarnings("deprecation")
public class PrintView extends View {
    private State state;
    private StoreState storeState;

    public PrintView(State s) {
        state = s;
        s.addObserver(this);
        storeState = s.getStore();
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub
        printEvents();
        
    }

    public void printParameter(){
        System.out.println("PARAMETRAR\n" + 
        "==========\n"+
        "Antal kassor, n.........: " + storeState.getMaxCheckouts() + "\n"+
        "Max som ryms, m.........: " + storeState.getMaxCustomers() + "\n"+
        "Ankomsthastighet, lambda..: " + storeState.getLambda() + "\n"+
        "Plocktider, [P_min..P_max]: " + "[" + storeState.getMinPickTime() +".." + storeState.getMaxPickTime() +"]\n" + 
        "Betaltider, [K_min..K_max]: " + "["+storeState.getMinPayTime()+".."+storeState.getMaxPayTime()+"]\n"+
        "Frö, f...................:" + storeState.getSeed() + "\n"+
        "FÖRLOPP\n" + "=======\n" + 
        "Tid Händelse  Kund  ?  led    ledT    I     $    :-(   köat    köT   köar  [Kassakö..]" );


    }
    public void printEvents(Event e){
        System.out.println(storeState.getTime() + " " + e.getName() + " " + e.getCustomer().getId() +
         " " + storeState.isOpen() ? 'Ö' : 'S' + " " + storeState.getFreeCheckouts() + " " + storeState.);
    }
    public void results(){

    }
/*
    a. tid,
    b. händelsenamn,
    c. kundnummer,
    d. om    det    är    öppet    eller    stängt,
    e. antal    lediga    kassor,
    f. summa    tid    kassor    varit    lediga,
    g. antal    kunder    inne    i    snabbköpet,
    h. antal    kunder    som    handlat    (dvs    kommit    in,    plockat,    betalat    och    lämnat),
    i. antal    missade    kunder,
    j. antal    kunder    som    köat,
    k. summa    tid    kunder    stått    i    kundkön,
    l. kassaköns    längd    och
    m. hela    kassakön,    där    det för    varje    par    av    kunder    gäller    att    den    som    står    till
    vänster    har    stått    längre    i    kön    än    den    som    står    till    hö
*/
