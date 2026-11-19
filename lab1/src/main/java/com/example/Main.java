package com.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Formatter;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Вариант с простыми типами:");
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        System.out.print("Введите x (в диапазоне от -1 до 1):\nx = ");
        double xSimple = scanner.nextDouble();

        System.out.print("Введите натуральное число k (для погрешности e = 10^-k):\nk = ");
        int kSimple = scanner.nextInt();

        double resultSimple = SeriesCalculator.calculateSimple(xSimple, kSimple);
        double standardResult = 1.0 / Math.sqrt(1.0 + xSimple);

        StringBuilder sb = new StringBuilder();
        Formatter formatter = new Formatter(sb);

        int intVal = (int) Math.round(resultSimple * 100);
        formatter.format("Округленное число %d в 8-й системе: %o\n", intVal, intVal);
        formatter.format("Округленное число %d в 16-й системе: %X\n", intVal, intVal);

        int precision = kSimple + 1;

        formatter.format("%-22s |%+15." + precision + "f|\n", "Вычисление:", resultSimple);
        formatter.format("%-22s |%+15." + precision + "f|\n", "Для сравнения:", standardResult);

        System.out.print(formatter.toString());
        formatter.close();

        System.out.printf("System.out.printf:     |%-+15." + precision + "f|\n\n", resultSimple);

        System.out.println("Вариант с большими типами:");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Введите x для BigDecimal: ");
            double xBig = Double.parseDouble(reader.readLine().trim().replace(',', '.'));

            System.out.print("Введите k для BigDecimal: ");
            int kBig = Integer.parseInt(reader.readLine().trim());

            BigDecimal resultBig = SeriesCalculator.calculateBig(xBig, kBig);

            System.out.println("Результат (BigDecimal): " + resultBig);

        } catch (IOException | NumberFormatException e) {
            System.out.println("Ошибка при чтении данных: " + e.getMessage());
        }

        scanner.close();
    }
}