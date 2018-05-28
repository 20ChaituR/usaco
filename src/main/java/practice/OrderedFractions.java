/*
ID: cravuri
LANG: JAVA
TASK: frac1
*/

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

class frac1 {

    static int N;
    static List<Fraction> allFractions = new ArrayList<Fraction>();

    public static void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("frac1.in"));
        N = sc.nextInt();
    }

    public static void allFractions() {
        Set<Fraction> orderedFractions = new HashSet<Fraction>();
        for (int den = 2; den <= N; den++) {
            for (int num = 1; num < N; num++) {
                if (num / den < 1) {
                    Fraction f = new Fraction(num, den);
                    f.simplify();
                    orderedFractions.add(f);
                }
            }
        }
        orderedFractions.add(new Fraction(0, 1));
        orderedFractions.add(new Fraction(1, 1));
        for (Fraction orderedFraction : orderedFractions) {
            allFractions.add(orderedFraction);
        }
        Collections.sort(allFractions, new Comparator<Fraction>() {
            @Override
            public int compare(Fraction o1, Fraction o2) {
                double n = o1.numerator / o1.denominator;
                double m = o2.numerator / o2.denominator;
                if (n > m) {
                    return 1;
                } else if (n < m) {
                    return -1;
                }
                return 0;
            }
        });
    }

    public static void writeFile() throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter("frac1.out"));
        for (Fraction fraction : allFractions) {
            text.write(((int) fraction.numerator) + "/" + ((int) fraction.denominator));
            text.newLine();
        }
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        allFractions();
        writeFile();
    }

    static class Fraction {
        double numerator;
        double denominator;

        public Fraction(int numerator, int denominator) {
            this.denominator = denominator;
            this.numerator = numerator;
        }

        double gcm(double a, double b) {
            return b == 0 ? a : gcm(b, a % b);
        }

        void simplify() {
            double gcm = gcm(numerator, denominator);
            numerator = numerator / gcm;
            denominator = denominator / gcm;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Fraction fraction = (Fraction) o;

            if (Double.compare(fraction.numerator, numerator) != 0) return false;
            return Double.compare(fraction.denominator, denominator) == 0;

        }

        @Override
        public int hashCode() {
            int result;
            long temp;
            temp = Double.doubleToLongBits(numerator);
            result = (int) (temp ^ (temp >>> 32));
            temp = Double.doubleToLongBits(denominator);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            return result;
        }
    }

}
