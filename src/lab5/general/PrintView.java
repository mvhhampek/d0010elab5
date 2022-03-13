package lab5.general;

import java.util.Observable;
import lab5.store.StoreState;

@SuppressWarnings("deprecation")
public class PrintView extends View {
    private StoreState storeState;
    private State state;

    public PrintView(State state, StoreState storeState) {
        this.storeState = storeState;
        this.state = state;
        state.addObserver(this);
    }

    public void update(Observable o, Object arg) {
        printEvents(storeState.getCurrentEvent());
    }

    public void printParameters() {
        System.out.println("PARAMETRAR\n" +
                "==========\n" +
                "Antal kassor, n...........: " + storeState.getMaxCheckouts() + "\n" +
                "Max som ryms, m...........: " + storeState.getMaxCustomers() + "\n" +
                "Ankomsthastighet, lambda..: " + storeState.getLambda() + "\n" +
                "Plocktider, [P_min..P_max]: " + "[" + storeState.getMinPickTime() + ".." + storeState.getMaxPickTime()
                + "]\n" +
                "Betaltider, [K_min..K_max]: " + "[" + storeState.getMinPayTime() + ".." + storeState.getMaxPayTime()
                + "]\n" +
                "Frö, f....................: " + storeState.getSeed() + "\n" +
                "FÖRLOPP\n" + "=======\n\n" +
                "Tid\tHändelse\tKund\t?\tled\tledT\tI\t$\t:-(\tköat\tköT\tköar\t[Kassakö..]");

    }

    public void printEvents(Event e) {
        switch (e.getName()) {
            case "Start":
                printParameters();
                System.out.println(String.format("%.2f", e.getTime()) + "\t" + e.getName());
                break;
            case "Stopp":
                System.out.println(String.format("%.2f", e.getTime()) + "\t" + e.getName());
                printResults();
                break;
            default:
                String customerId = e.getName() == "Stäng" ? "---"
                        : Integer.toString(storeState.getCurrentCustomer().getId());
                String open = storeState.isOpen() ? "Ö" : "S";
                System.out.println(
                        // a. Time
                        String.format("%.2f", e.getTime()) + "\t" +
                        // b. Event name
                                e.getName() + "\t\t" +
                                // c. Customer ID
                                customerId + "\t" +
                                // d. Closed or open
                                open + "\t" +
                                // e. #Free checkouts
                                storeState.getFreeCheckouts() + "\t" +
                                // f. Sum time of free checkouts
                                String.format("%.2f", storeState.getFreeCheckoutTime()) + "\t" +
                                // g. #Customers in store
                                storeState.getCustomersInStore() + "\t" +
                                // h. #Payed customers
                                storeState.getPayedCustomers() + "\t" +
                                // i. #Missed customers
                                storeState.getMissedCostumers() + "\t" +
                                // j. #Customer who have queued
                                storeState.getCustomersQueued() + "\t" +
                                // k. Sum time customers have queued
                                String.format("%.2f", storeState.getTotalQueueTime()) + "\t" +
                                // l. Checkoutsqueue length
                                storeState.getCustomerQueue().size() + "\t" +
                                // m. Checkoutqueue with customer ID
                                storeState.getCustomerQueue().toString());
                break;
        }
    }

    public void printResults() {
        System.out.println("============000============");
    }
}
/*
 * a. tid,
 * b. händelsenamn,
 * c. kundnummer,
 * d. om det är öppet eller stängt,
 * e. antal lediga kassor,
 * f. summa tid kassor varit lediga,
 * g. antal kunder inne i snabbköpet,
 * h. antal kunder som handlat (dvs kommit in, plockat, betalat och lämnat),
 * i. antal missade kunder,
 * j. antal kunder som köat,
 * k. summa tid kunder stått i kundkön,
 * l. kassaköns längd och
 * m. hela kassakön, där det för varje par av kunder gäller att den som står
 * till
 * vänster har stått längre i kön än den som står till hö
 * 
 * 
 * 
 * e.getName() + " " + 0 + " " + customerId +
 * " " + open + " " + storeState.getFreeCheckouts() + " " + 0 + " "
 * + storeState.getCustomersInStore()
 * + " " + 0 + " " + storeState.getMissedCostumers() + " " +
 * storeState.getCustomersQueued() + " "
 * + 0 + " " + storeState.getCustomerQueue().toString())
 */
