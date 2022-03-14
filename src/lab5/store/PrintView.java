package lab5.store;

import java.util.Observable;

import lab5.general.Event;
import lab5.general.State;
import lab5.general.View;

/**
 * Specific view of the store
 * 
 * @author Hampus Kämppi, Gustav Edner, Jonathan Junel, Linus Karlsson
 *
 */
@SuppressWarnings("deprecation")
public class PrintView extends View {
    private StoreState storeState;

    /**
     * Constructor
     * 
     * @param state      The state of the simulator
     * @param storeState The state of the store
     */
    public PrintView(State state, StoreState storeState) {
        this.storeState = storeState;
        state.addObserver(this);
    }

    /**
     * Called whenever this is notified
     */
    public void update(Observable o, Object arg) {
        printEvents(storeState.getCurrentEvent());
    }

    /**
     * Prints simulation parameters
     */
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
                "\nFÖRLOPP\n" + "=======\n" +
                "Tid\tHändelse\tKund\t?\tled\tledT\tI\t$\t:-(\tköat\tköT\tköar\t[Kassakö..]");

    }

    /**
     * Prints details from the storestate
     * 
     * @param e The current event
     */
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

    /**
     * Prints simulation results
     */
    public void printResults() {
        System.out.println("\nRESULTAT" +
                "\n========" +

                "\n1) Av " + (storeState.getMissedCostumers() + storeState.getPayedCustomers()) + " kunder handlade "
                + storeState.getPayedCustomers() + " medan " + storeState.getMissedCostumers() + " missades.\n" +

                "\n2) Total tid " + storeState.getMaxCheckouts() + " kassor lediga: "
                + String.format("%.2f", storeState.getFreeCheckoutTime()) + " te.\n" +
                "Genomsnittlig ledig kassatid: "
                + String.format("%.2f", (storeState.getFreeCheckoutTime() / storeState.getMaxCheckouts())) + " (dvs. "
                + String.format("%.2f",
                        ((storeState.getFreeCheckoutTime() / storeState.getMaxCheckouts())
                                / storeState.getLastPayTime()) * 100)
                + "% av tiden från öppning tills sista kunden betalat).\n" +

                "\n3) Total tid " + storeState.getCustomersQueued() + " kunder tvingats köa: "
                + String.format("%.2f", storeState.getTotalQueueTime()) + " te."
                + "\nGenomsnittlig kötid: "
                + String.format("%.2f", storeState.getTotalQueueTime() / storeState.getCustomersQueued()) + " te.");
    }
}