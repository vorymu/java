package com.example;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

public class SeriesTest {

    @Test
    public void testCalculateSimple() {
        double x = 0.5;
        int k = 3;
        double result = SeriesCalculator.calculateSimple(x, k);
        double expected = 1.0 / Math.sqrt(1.0 + x);
        assertEquals(expected, result, 1e-2, "Результаты простого типа должны совпадать со стандартными");
    }

    @Test
    public void testCalculateBig() {
        double x = 0.2;
        int k = 4;
        BigDecimal result = SeriesCalculator.calculateBig(x, k);
        assertNotNull(result, "Результат BigDecimal не должен быть null");
        assertTrue(result.doubleValue() > 0, "Результат должен быть положительным");
    }

    @Test
    public void testMaxXForSimpleTypes() {
        double maxX = 0.0;
        int k = 3;
        
        for (double x = 0.1; x < 0.9999; x += 0.01) {
            try {
                double res = SeriesCalculator.calculateSimple(x, k);
                if (Double.isNaN(res) || Double.isInfinite(res)) {
                    break;
                }
                maxX = x;
            } catch (Exception e) {
                break;
            }
        }
java.io.PrintStream utf8Out = new java.io.PrintStream(System.out, true, java.nio.charset.StandardCharsets.UTF_8);
utf8Out.println("[UNIT-TEST] Максимальное протестированное X без переполнения: " + maxX);
        assertTrue(maxX > 0, "Максимальное X должно быть найдено");
    }
}