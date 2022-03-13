package lab5;

import lab5.store.StoreState;

import java.util.Random;

public class Optimize {

    /**
     * metod1 kör RunSim med bestämda parametrar
     * @param kassor kassor bestämmer hur många kassor metod1 ska köras med
     * @param frö frö bestämmer med vilket frö metod1 ska köras med
     * @return returnerar storeState med alla slutvärden.
     */
    public StoreState metod1(int kassor, long frö){
        StoreState storeState = new StoreState(kassor,15, 4, 0.5, 1.00, 0.5, 1.0, frö,8.0);
        //View view = new View();
        //State state = new State(storeState, view);
        //Simulator simulator = new Simulator(state);
        //simulator.run();
        return storeState;
        }

    /**
     * metod2 kör en for-loop som anroppar metod1 flera gånger med olika antal kassor för att hitta minst antal kassor så att så få som möjligt missas.
     * @param frö frö bestämmer vilket frö metoden ska köras med
     * @return returnerar minst antal kassor som ger minns missade kunder.
     */
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

    /**
     * metod3 kör metod2 som kör metod1. metod3 letar efter den mest rimmliga antal kassor, för metod2 kan ge olika värden. Efter 100 varv av samma svar avbryts metod 3
     * @param f f är ett random heltal
     * @return returnerar det högstMinsta antal kassor.
     */
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
