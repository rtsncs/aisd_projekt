package org.example;

import org.junit.jupiter.api.Test;

class PolynomialTest {

    @Test
    void add() {
        var A = new Polynomial(1., 2., 3.);
        var B = new Polynomial(3., 2., 1.);
        assert (Polynomial.add(A, B).equals(new Polynomial(4., 4., 4.)));
        assert (Polynomial.add(B, A).equals(new Polynomial(4., 4., 4.)));

        A = new Polynomial(1., 2., 3.);
        B = new Polynomial(3., 2.);
        assert (Polynomial.add(A, B).equals(new Polynomial(4., 4., 3.)));
        assert (Polynomial.add(B, A).equals(new Polynomial(4., 4., 3.)));

        assert (Polynomial.add(A, A).equals(new Polynomial(2., 4., 6.)));
    }

    @Test
    void subtract() {
        var A = new Polynomial(1., 2., 3.);
        var B = new Polynomial(3., 2., 1.);
        assert (Polynomial.subtract(A, B).equals(new Polynomial(-2., 0., 2.)));
        assert (Polynomial.subtract(B, A).equals(new Polynomial(2., 0., -2.)));

        A = new Polynomial(1., 2., 3.);
        B = new Polynomial(3., 2.);
        assert (Polynomial.subtract(A, B).equals(new Polynomial(-2., 0., 3.)));
        assert (Polynomial.subtract(B, A).equals(new Polynomial(2., 0., -3.)));

        assert (Polynomial.subtract(B, B).equals(new Polynomial(0., 0.)));
    }

    @Test
    void testToString() {
        var A = new Polynomial(1., 2., 3.);
        System.out.println(A);
        assert (A.toString().equals("3.0x^2 + 2.0x^1 + 1.0"));

        var B = new Polynomial(-3., 2.);
        System.out.println(B);
        assert (B.toString().equals("2.0x^1 + -3.0"));
    }

    @Test
    void multiply() {
        var A = new Polynomial(-1., 2.);
        var B = new Polynomial(-6., 5.);
        System.out.println(Polynomial.multiply(A, B));
        assert (Polynomial.multiply(A, B).equals(new Polynomial(6., -17., 10.)));

        A = new Polynomial(-1., 2., 1.);
        B = new Polynomial(6., -3., 2.);
        System.out.println(Polynomial.multiply(A, B));
        assert (Polynomial.multiply(A, B).equals(new Polynomial(-6., 15., -2., 1., 2.)));
    }

    @Test
    void parse() {
        assert (Polynomial.parse("2x^4 + 1x^3 + 2x^2 + 15x^1 + -6x^0").equals(new Polynomial(-6., 15., 2., 1., 2.)));
        assert (Polynomial.parse("2x^4+x^3+2x^2+15x+-6").equals(new Polynomial(-6., 15., 2., 1., 2.)));

    }

    @Test
    void getDegree() {
        var A = new Polynomial(-1., 2.);
        assert (A.getDegree() == 1);
        var B = new Polynomial(6., -3., 2.);
        assert (B.getDegree() == 2);
        var C = new Polynomial(6., -3., 2., 7.);
        assert (C.getDegree() == 3);
        var D = new Polynomial(6., -3., 2., 7., 1.);
        assert (D.getDegree() == 4);
        var E = new Polynomial(6.);
        assert (E.getDegree() == 0);
    }

    @Test
    void getValue() {
        var A = new Polynomial(-1., 2.);
        assert (A.getValue(1.) == 1.);
        var B = new Polynomial(6., -3., 2.);
        assert (B.getValue(2.) == 8.);
        var C = new Polynomial(6., -3., 2., 7.);
        assert (C.getValue(3.) == 204.);
        var D = new Polynomial(6., -3., 2., 7., 1.);
        assert (D.getValue(4.) == 730.);
        var E = new Polynomial(6.);
        assert (E.getValue(5.) == 6.);
    }
}