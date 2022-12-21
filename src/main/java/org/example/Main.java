package org.example;

import java.io.BufferedReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Polynomial A = new Polynomial(0.);
        Polynomial B = new Polynomial(0.);
        System.out.println("Dostępne komendy:");
        System.out.println("A=**wielomian**");
        System.out.println("B=**wielomian**");
        System.out.println("x=**wartość** - obliczenie wartości A i B dla danego x");
        System.out.println("add - A+B");
        System.out.println("sub - A-B");
        System.out.println("mul - A*B");
        System.out.println("swap - zamiana A i B");

        try (var reader = new BufferedReader(System.console().reader())) {
            while (true) {
                System.out.println("------------------------------------------------------");
                System.out.println("A(x) = " + A);
                System.out.println("B(x) = " + B);
                System.out.println("------------------------------------------------------");

                String str = reader.readLine().trim().toLowerCase();
                if (str.equals("q"))
                    System.exit(0);
                else if (str.startsWith("a="))
                    A = Polynomial.parse(str.substring(2));
                else if (str.startsWith("b="))
                    B = Polynomial.parse(str.substring(2));
                else if (str.startsWith("x=")) {
                    double x = Double.parseDouble(str.substring(2));
                    System.out.println("A(" + x +")= " + A.getValue(x));
                    System.out.println("B(" + x +")= " + B.getValue(x));
                }
                else if (str.equals("add")) {
                    System.out.println("A + B = " + Polynomial.add(A, B));
                }
                else if (str.equals("sub")) {
                    System.out.println("A - B = " + Polynomial.subtract(A, B));
                }
                else if (str.equals("mul")) {
                    System.out.println("A * B = " + Polynomial.multiply(A, B));
                }
                else if (str.equals("swap")) {
                    var C = new Polynomial(A.getCoefficients());
                    A = B;
                    B = C;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}