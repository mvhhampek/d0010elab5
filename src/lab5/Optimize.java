package lab5;

import lab5.store.StoreState;

public class Optimize {

    public StoreState metod1(int kassor, int frö){
        StoreState storeState = new StoreState(kassor,15, 4, 0.5, 1.00, 0.5, 1.0, frö,8.0);
        //View view = new View();
        //State state = new State(storeState, view);
        //Simulator simulator = new Simulator(state);
        //simulator.run();

        return storeState;
        }

    public int metod2(int frö){
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
    public int metod3(int f){
        boolean run = true;
        int högstaMinsta = metod2(f);
        int loop = 100;
        while(run){
            if(loop == 0){
                return högstaMinsta;
            }
            if(metod2(f) > högstaMinsta){
                högstaMinsta = metod2(f);
                loop = 100;
            }
            if(metod2(f) == högstaMinsta){
                loop--;
            }

        }
    }
}
