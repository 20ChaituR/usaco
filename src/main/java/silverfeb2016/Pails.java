package silverfeb2016;/*
ID: cravuri
LANG: JAVA
TASK: silverfeb2016.Pails
*/

import java.io.*;
import java.util.*;

public class Pails {

    static int X;
    static int Y;
    static int K;
    static int M;

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("pails.in"));
        StringTokenizer st = new StringTokenizer(text.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
    }

    public static int findMinError() {
        Set<Pair> s = allPoss(K);
        int min = Integer.MAX_VALUE;
        for (Pair p : s) {
            int x = Math.abs(p.x + p.y - M);
            if (x < min) {
                min = x;
            }
        }
        return min;
    }

    public static Set<Pair> allPoss(int steps) {
        if (steps == 0) {
            Set<Pair> a = new HashSet<>();
            a.add(new Pair(0, 0));
            return a;
        }
        Set<Pair> past = allPoss(steps - 1);
        Set<Pair> present = new HashSet<>();
        equals(present, past);
        for (Pair combo : past) {
            int a = combo.x;
            int b = combo.y;
            present.add(new Pair(0, b));
            present.add(new Pair(a, 0));
            present.add(new Pair(X, b));
            present.add(new Pair(a, Y));
            if (a + b > Y) {
                present.add(new Pair(a + b - Y, Y));
            } else {
                present.add(new Pair(0, a + b));
            }
            if (a + b > X) {
                present.add(new Pair(X, a + b - X));
            } else {
                present.add(new Pair(a + b, 0));
            }
        }
        return present;
    }

    public static void equals(Set<Pair> a, Set<Pair> b) {
        for (Pair element : b) {
            a.add(new Pair(element.x, element.y));
        }
    }

    public static void writeFile(int a) throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter("pails.out"));
        text.write(a + "");
        text.newLine();
        text.close();
    }

    public static void main(String[] args) throws IOException {
//        X = 14;
//        Y = 50;
//        K = 2;
//        M = 32;
//        int a = findMinError();
//        int asdf = 0;
        readFile();
        writeFile(findMinError());
    }

    static class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            return x == pair.x && y == pair.y;

        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }

}
