package com.example;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class SeriesCalculator {

    public static double calculateSimple(double x, int k) {
        double epsilon = Math.pow(10, -k);
        double sum = 1.0;
        double term = 1.0; 
        int n = 1;

        while (true) {
            term = term * (-(2.0 * n - 1) / (2.0 * n) * x);

            if (Math.abs(term) < epsilon) {
                break;
            }
            sum += term;
            n++;
            if (n > 10000) break;
        }
        return sum;
    }

    public static BigDecimal calculateBig(double xDouble, int k) {
        BigDecimal x = BigDecimal.valueOf(xDouble);
        BigDecimal epsilon = BigDecimal.ONE.scaleByPowerOfTen(-k);
        BigDecimal sum = BigDecimal.ONE;
        BigDecimal term = BigDecimal.ONE;
        int n = 1;

        MathContext mc = new MathContext(k + 10, java.math.RoundingMode.HALF_UP);

        while (true) {
            BigInteger nInt = BigInteger.valueOf(n);
            BigInteger twoNInt = nInt.multiply(BigInteger.valueOf(2));
            BigInteger twoNMinus1Int = twoNInt.subtract(BigInteger.ONE);

            BigDecimal twoN = new BigDecimal(twoNInt);
            BigDecimal twoNMinus1 = new BigDecimal(twoNMinus1Int);

            BigDecimal multiplier = twoNMinus1.negate().divide(twoN, mc).multiply(x, mc);
            
            term = term.multiply(multiplier, mc);

            if (term.abs().compareTo(epsilon) < 0) {
                break;
            }
            sum = sum.add(term, mc);
            n++;
            if (n > 10000) break;
        }
        return sum;
    }
}