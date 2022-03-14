package lab5.store;

/**
 * Class that represents the creation of customers
 * 
 * @author Hampus Kämppi, Gustav Edner, Jonathan Junel, Linus Karlsson
 *
 */
public class CustomerFactory {
    private static int customerIdCreated = 0;

    /**
     * Creates a customer
     * 
     * @return new customer
     */
    public Customer createCustomer() {
        return new Customer(customerIdCreated++);
    }
}
