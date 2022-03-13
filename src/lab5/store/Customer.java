package lab5.store;

/**
 * Class to represent customers
 * @author Hampus Kämppi, Gustav Edner, Jonathan Junel, Linus Karlsson
 *
 */
public class Customer {
    private int customerId;
    
    /**
     * Constructor
     * @param id	id number used to identify customers
     */
    public Customer(int id){
        customerId=id;
    }
    
    /**
     * Returns customers id
     * @return this customers id
     */
    public int getId(){
        return customerId;
    }
}
