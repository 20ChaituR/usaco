/*
ID: cravuri
LANG: JAVA
TASK: crypt1
*/

import java.io.*;
import java.util.*;

class crypt1 {

    static Set<Integer> digits = new HashSet<Integer>();

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("crypt1.in"));
        int n = Integer.parseInt(text.readLine());
        String ln = text.readLine();
        StringTokenizer st = new StringTokenizer(ln);
        for (int i = 0; i < n; i++) {
            digits.add(Integer.valueOf(st.nextToken()));
        }
    }

    public static int allCombos() {
        int numOfCombos = 0;
        for (int a : digits) {
            for (int b : digits) {
                for (int c : digits) {
                    for (int d : digits) {
                        for (int e : digits) {
                            if (multiply(a, b, c, d, e)) {
                                numOfCombos++;
                            }
                        }
                    }
                }
            }
        }
        return numOfCombos;
    }

    public static boolean multiply(int a, int b, int c, int d, int e) {
        int abc = 100*a + 10*b + c;
        int n = abc*e;
        String[] s = Integer.toString(n).split("");
        if (s.length != 3) {
            return false;
        }
        for (String value : s) {
            if (!digits.contains(Integer.valueOf(value))) {
                return false;
            }
        }
        int m = abc*d;
        s = Integer.toString(m).split("");
        if (s.length != 3) {
            return false;
        }
        for (String value : s) {
            if (!digits.contains(Integer.valueOf(value))) {
                return false;
            }
        }
        int x = n + 10*m;
        s = Integer.toString(x).split("");
        if (s.length != 4) {
            return false;
        }
        for (String value : s) {
            if (!digits.contains(Integer.valueOf(value))) {
                return false;
            }
        }
        return true;
    }

    public static void writeFile(int numOfCombos) throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter("crypt1.out"));
        text.write(numOfCombos + "");
        text.newLine();
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        writeFile(allCombos());
    }
}
