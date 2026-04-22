package Strategies;

import java.util.function.Function;

public class SimpsonStrategy implements ApproximationStrategy{

    @Override
    public double approximate(Function<Double, Double> function, double r, int partitions) {
        double delta = r/partitions;
        double approximation = 0;
        approximation += function.apply(0.0);
        approximation += function.apply(r);
        for(int i = 1; i < partitions; i++){
            if(i%2 == 0){
                approximation += 2*function.apply(i*delta);
            } else {
                approximation += 4*function.apply(i*delta);
            }
        }
        approximation *= delta/3;
        return approximation;
    }
}
