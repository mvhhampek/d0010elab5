package lab5.general;

import java.util.Observable;
import lab5.store.StoreState;

@SuppressWarnings("deprecation")
public class PrintView extends View {
    private StoreState storeState;

    public PrintView(State state, StoreState storeState) {
        this.storeState = storeState;
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
                        String.format("%.2f", e.getTime()) + "\t" +
                                e.getName() + "\t\t" +
                                customerId + "\t" +
                                open + "\t" +
                                storeState.getFreeCheckouts() + "\t" +
                                String.format("%.2f", storeState.getFreeCheckoutTime()) + "\t" +
                                storeState.getCustomersInStore() + "\t" +
                                storeState.getPayedCustomers() + "\t" +
                                storeState.getMissedCostumers() + "\t" +
                                storeState.getCustomersQueued() + "\t" +
                                String.format("%.2f", storeState.getTotalQueueTime()) + "\t" +
                                storeState.getCustomerQueue().size() + "\t" +
                                storeState.getCustomerQueue().toString());
                // a. Time
                // b. Event name
                // c. Customer ID
                // d. Closed or open
                // e. #Free checkouts
                // f. Sum time of free checkouts
                // g. #Customers in store
                // h. #Payed customers
                // i. #Missed customers
                // j. #Customer who have queued
                // k. Sum time customers have queued
                // l. Checkoutsqueue length
                // m. Checkoutqueue with customer ID
                break;
        }
    }

    public void printResults() {
        System.out.println("============000============");
    }
}