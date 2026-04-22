package Strategies;

import java.util.function.Function;

public interface ApproximationStrategy {
    double approximate(Function<Double, Double> function, double r, int partitions);
}
