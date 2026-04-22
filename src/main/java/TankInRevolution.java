import Strategies.ApproximationStrategy;

import java.util.function.Function;

public class TankInRevolution {
    Function<Double, Double> function;
    ApproximationStrategy strategy;
    int partitions;
    double width;

    public TankInRevolution(Function<Double, Double> function, ApproximationStrategy strategy, double width) {
        this.function = function;
        this.strategy = strategy;
        this.partitions = 50;
        this.width = width;
    }

    public void setStrategy(ApproximationStrategy strategy) {
        this.strategy = strategy;
    }

    public void setPartitions(int partitions) {
        this.partitions = partitions;
    }

    public double approximateCapacity() {
        return Math.PI * strategy.approximate(function.andThen(x->x*x), width, partitions);
    }
}
