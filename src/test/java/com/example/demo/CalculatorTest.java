package com.example.demo;

import com.example.demo.service.Calculator;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculatorTest {

    @Test
    public void testAdd() {
        Calculator calculator = new Calculator();
        int result = calculator.add(3, 5);
        assertEquals(8, result);
    }

    @Test
    public void testSubtract() {
        Calculator calculator = new Calculator();
        int result = calculator.sub(5, 3);
        assertEquals(2, result);
    }


}

class Myclass implements Cloneable{
    private int value;
    public Myclass(int value) {
        this.value = value;
    }
}