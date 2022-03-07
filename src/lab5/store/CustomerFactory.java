package lab5.store;

public class CustomerFactory {
    private static int costomerIdCreated=0;
    public Customer createCustomer(){
    	Customer C=new Customer(costomerIdCreated);
        costomerIdCreated++;
        return C;
    }
}
