package org.example;

import java.io.BufferedReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Polynomial A = new Polynomial(0.);
        Polynomial B = new Polynomial(0.);

        try (var reader = new BufferedReader(System.console().reader())) {
            while (true) {
                System.out.println("A(x) = " + A);
                System.out.println("B(x) = " + B);

                String str = reader.readLine().trim().toLowerCase();
                if (str.equals("q"))
                    System.exit(0);
                if (str.startsWith("a="))
                    A = Polynomial.parse(str.substring(2));
                if (str.startsWith("b="))
                    B = Polynomial.parse(str.substring(2));
                if (str.startsWith("x=")) {
                    double x = Double.parseDouble(str.substring(2));
                    System.out.println(A.getValue(x));
                    System.out.println(B.getValue(x));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}