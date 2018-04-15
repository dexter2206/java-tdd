package gcd;

import org.junit.Test;

import static org.junit.Assert.*;

public class GCDTest {

    @Test
    public void whenNonzeroNumbersPassedComputesGCD() {
        assertEquals(11, GCD.compute(33, 44));
        assertEquals(8, GCD.compute(24, 16));
    }

    @Test
    public void whenOneNumberIsZeroSecondIsGCD() {
        assertEquals(21, GCD.compute(0, 21));
        assertEquals(21, GCD.compute(21, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenBothNumbersAreZeroExceptionIsThrown(){
        GCD.compute(0, 0);
    }

    @Test
    public void whenNegativeNumbersPassedGCDIsPositive() {
        assertEquals(7, GCD.compute(35, -21));
        assertEquals(7, GCD.compute(-35, 21));
        assertEquals(7, GCD.compute(-35, -21));
    }
}