import Strategies.ApproximationStrategy;
import Strategies.SimpsonStrategy;
import Strategies.TrapezeStrategy;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class TankInRevolutionTest {

    private TankInRevolution tank;
    private ApproximationStrategy simpson;
    private ApproximationStrategy trapeze;

    @BeforeEach
    void setUp() {
        simpson = new SimpsonStrategy();
        trapeze = new TrapezeStrategy();
        tank = new TankInRevolution(x -> 1.0, simpson, 1.0);
    }

    @Test
    void setStrategy() {
        // Test setting strategy
        TankInRevolution tank = new TankInRevolution(x -> x, simpson, 1.0);
        tank.setPartitions(3);
        double resultSimpson = tank.approximateCapacity();
        tank.setStrategy(trapeze);
        double resultTrapeze = tank.approximateCapacity();
        // They should be different (approximately)
        assertNotEquals(resultSimpson, resultTrapeze, 0.1);
    }

    @Test
    void setPartitions() {
        // Test setting partitions
        tank.setPartitions(100);
        // Test with a known function: constant radius 1, width 1, volume = π * 1^2 * 1 = π
        double result = tank.approximateCapacity();
        assertEquals(Math.PI, result, 0.01);
    }

    @Test
    void approximateCapacity() {
        // Test cylindrical tank: constant radius 1, width 2, exact volume = π * 1^2 * 2 = 2π
        TankInRevolution cylTank = new TankInRevolution(x -> 1.0, simpson, 2.0);
        cylTank.setPartitions(10);
        double cylVolume = cylTank.approximateCapacity();
        assertEquals(2 * Math.PI, cylVolume, 0.01);

        // Test conical tank: linear radius h (from 0 to 1), width 1, exact volume = (1/3)π * 1^2 * 1 = π/3
        TankInRevolution coneTank = new TankInRevolution(x -> x, simpson, 1.0);
        double coneVolume = coneTank.approximateCapacity();
        assertEquals(Math.PI / 3.0, coneVolume, 0.01);

        // Test with trapeze strategy
        TankInRevolution cylTankTrapeze = new TankInRevolution(x -> 1.0, trapeze, 2.0);
        double cylVolumeTrapeze = cylTankTrapeze.approximateCapacity();
        assertEquals(2 * Math.PI, cylVolumeTrapeze, 0.01);
    }
}