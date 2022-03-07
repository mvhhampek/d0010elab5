package lab5.store.time;

import java.util.Random;

public class UniformRandomStream{
    
    private Random random;
    private double intervall;
    private double min;

    public UniformRandomStream(double min, double max, long seed){
        random = new Random(seed);
        this.min = min;
        intervall = max - min;
    }    

    /**
     * Returns random double within min and max
     */
    public double next(){
        return intervall * random.nextDouble() + min;
    }

}