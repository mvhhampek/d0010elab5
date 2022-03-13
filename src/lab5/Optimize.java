package lab5;

import lab5.store.StoreState;

import java.util.Random;

public class Optimize {

    public StoreState metod1(int kassor, long frö){
        StoreState storeState = new StoreState(kassor,15, 4, 0.5, 1.00, 0.5, 1.0, frö,8.0);
        //View view = new View();
        //State state = new State(storeState, view);
        //Simulator simulator = new Simulator(state);
        //simulator.run();
        return storeState;
        }

    public int metod2(long frö){
        int antalCustomer = metod1(1,frö).getMaxCustomers();
        int antalKassor = 0;
        int antalMissade = antalCustomer;
        for(int i = 1; i <= antalCustomer; i++){
            if(metod1(i,frö).getMissedCostumers() < antalMissade){
                antalMissade = metod1(i,frö).getMissedCostumers();
                antalKassor = i;
            }
            if(antalMissade == 0){
                return antalKassor;
            }
        }
        return antalKassor;
    }
    public int metod3(Random f){
        f = new Random();
        boolean run = true;
        int högstaMinsta = metod2(f.nextLong());
        int loop = 100;
        while(run){
            if(loop == 0){
                run  = false;
            }
            if(metod2(f.nextLong()) > högstaMinsta){
                högstaMinsta = metod2(f.nextLong());
                loop = 100;
            }
            if(metod2(f.nextLong()) == högstaMinsta){
                loop--;
            }
        }
        return högstaMinsta;
    }
}
