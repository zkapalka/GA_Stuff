package org.example.calculator.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@SpringBootTest
public class CalculatorServiceTest {

    @Autowired
    CalculatorService calculatorService;

    @Test
    public void testAddition() {
        int result = calculatorService.add(3, 4);
        assertEquals(7, result);
    }

    @Test
    public void testSubtraction() {
        int result = calculatorService.subtract(10, 5);
        assertEquals(5, result);
    }

    @Test
    public void testMultiplication() {
        int result = calculatorService.multiply(2, 6);
        assertEquals(12, result);
    }

    @Test
    public void testDivision() {
        int result = calculatorService.divide(15, 3);
        assertEquals(5, result);
    }

    @Test
    public void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> calculatorService.divide(10, 0));
    }
}

