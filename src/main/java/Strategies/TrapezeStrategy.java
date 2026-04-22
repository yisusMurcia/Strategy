package Strategies;

import java.util.function.Function;

public class TrapezeStrategy implements ApproximationStrategy{

    @Override
    public double approximate(Function<Double, Double> function, double r, int partitions) {
        double delta = r/partitions;
        double approximation = function.apply(0.0);
        approximation += function.apply(r);
        for(int i = 1; i < partitions; i++){
            approximation += 2*function.apply(i*delta);
        }
        approximation *= delta/2;
        return approximation;
    }
}