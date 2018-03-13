import org.junit.Before;
import org.junit.Test;
import pack1.Calculator;

import static org.junit.Assert.*;

public class CalculatorTest {

    Calculator calculator;
    @Before
    public void Init(){
        calculator = new Calculator();
    }

    @Test
    public void add() {
        assertEquals(9, calculator.Add(5,4));
    }

    @Test
    public void sub() {
        assertEquals(1, calculator.Sub(5,4));
    }
}