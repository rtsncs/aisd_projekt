package org.example;

import java.util.*;

public class Polynomial {
    private List<Double> coefficients;

    public Polynomial(Double... coefficients) {
        this.coefficients = new ArrayList<>(Arrays.asList(coefficients));
    }

    public Polynomial(List<Double> coefficients) {
        this.coefficients = coefficients;
    }

    static Polynomial add(final Polynomial A, final Polynomial B) {
        List<Double> coefficients;
        if (A.getDegree() > B.getDegree()) {
            coefficients = new ArrayList<>(A.getCoefficients());
            for (int i = 0; i <= B.getDegree(); i++) {
                coefficients.set(i, coefficients.get(i) + B.getCoefficient(i));
            }
        } else {
            coefficients = new ArrayList<>(B.getCoefficients());
            for (int i = 0; i <= A.getDegree(); i++) {
                coefficients.set(i, coefficients.get(i) + A.getCoefficient(i));
            }
        }
        return new Polynomial(coefficients);
    }

    static Polynomial subtract(final Polynomial A, final Polynomial B) {
        List<Double> coefficients;
        if (A.getDegree() > B.getDegree()) {
            coefficients = new ArrayList<>(A.getCoefficients());
            for (int i = 0; i <= B.getDegree(); i++) {
                coefficients.set(i, coefficients.get(i) - B.getCoefficient(i));
            }
        } else {
            coefficients = new ArrayList<>(B.getCoefficients());
            for (int i = 0; i <= B.getDegree(); i++) {
                if (i <= A.getDegree())
                    coefficients.set(i, -coefficients.get(i) + A.getCoefficient(i));
                else
                    coefficients.set(i, -coefficients.get(i));
            }
        }
        return new Polynomial(coefficients);
    }

    static Polynomial multiply(Polynomial A, Polynomial B) {
        var coefficients = new ArrayList<>(Collections.nCopies(A.getDegree() + B.getDegree() + 1, 0.0));

        for (int ia = 0; ia <= A.getDegree(); ia++) {
            for (int ib = 0; ib <= B.getDegree(); ib++) {
                int i = ia + ib;
                coefficients.set(i, coefficients.get(i) + A.getCoefficient(ia) * B.getCoefficient(ib));
            }
        }

        return new Polynomial(coefficients);
    }

    static Polynomial parse(String s) throws PolynomialFormatException {
        var coefficients = new ArrayList<Double>();
        var a = s.split("\\+");

        for (var e : a) {
            var trimmed = e.trim();
            var x = trimmed.split("x\\^");
            int degree;
            double coefficient;
            if (x.length == 1) {
                if (trimmed.charAt(trimmed.length() - 1) == 'x') {
                    degree = 1;
                    coefficient = Double.parseDouble(trimmed.substring(0, trimmed.length() - 1));
                } else {
                    degree = 0;
                    coefficient = Double.parseDouble(trimmed);
                }
            } else if (x.length == 2) {
                if (x[0].isEmpty()) {
                    degree = Integer.parseInt(trimmed.substring(2));
                    coefficient = 1.0;
                } else {
                    degree = Integer.parseInt(x[1]);
                    coefficient = Double.parseDouble(x[0]);
                }
            } else {
                throw new PolynomialFormatException();
            }
            if (degree >= coefficients.size()) {
                coefficients.ensureCapacity(degree + 1);
                int i = coefficients.size();
                while (degree > i) {
                    coefficients.add(0.);
                    i++;
                }
                coefficients.add(coefficient);
            } else {
                coefficients.set(degree, coefficients.get(degree) + coefficient);
            }
        }

        return new Polynomial(coefficients);
    }

    public double getValue(double x) {
        //return getValue(x, getDegree());
        double result = getCoefficient(getDegree());
        for (int i = getDegree() - 1; i >= 0; i--)
            result = result * x + getCoefficient(i);

        return result;
    }

    private double getValue(double x, int degree) {
        if (degree == 0)
            return coefficients.get(coefficients.size() - 1);
        return x * getValue(x, degree - 1) + coefficients.get(coefficients.size() - degree - 1);
    }

    public int getDegree() {
        return coefficients.size() - 1;
    }

    public List<Double> getCoefficients() {
        return coefficients;
    }

    public void setCoefficients(List<Double> coefficients) {
        this.coefficients = coefficients;
    }

    public double getCoefficient(int i) {
        return coefficients.get(i);
    }

    public double setCoefficient(int i, double value) {
        return coefficients.set(i, value);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int i = getDegree(); i >= 0; i--) {
            if (i != getDegree()) {
                string.append(" + ");
            }
            string.append(coefficients.get(i));
            if (i != 0) {
                string.append("x^").append(i);
            }
        }

        return string.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Polynomial that = (Polynomial) o;
        return coefficients.equals(that.coefficients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coefficients);
    }
}
