/*
ID: cravuri
LANG: JAVA
TASK: pprime
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class pprime {

    static int a;
    static int b;
    static List<Integer> primePalindromes = new ArrayList<Integer>();

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("pprime.in"));
        StringTokenizer st = new StringTokenizer(text.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
    }

    private static void allPalindromes() {
        // 1 digit
        if (a < 10 && b > 0) {
            for (int d1 = 5; d1 <= 9; d1 += 2) {
                int x = d1;
                if (x >= a && x <= b && isPrime(x)) {
                    primePalindromes.add(x);
                }
            }
        }
        // 2 digit
        if (a < 100 && b > 9) {
            for (int d1 = 1; d1 <= 9; d1 += 2) {
                int x = 10*d1 + d1;
                if (x >= a && x <= b && isPrime(x)) {
                    primePalindromes.add(x);
                }
            }
        }
        // 3 digit
        if (a < 1000 && b > 99) {
            for (int d1 = 1; d1 <= 9; d1 += 2) {
                for (int d2 = 0; d2 <= 9; d2++) {
                    int x = 100 * d1 + 10 * d2 + d1;
                    if (x >= a && x <= b && isPrime(x)) {
                        primePalindromes.add(x);
                    }
                }
            }
        }
        // 4 digit
        if (a < 10000 && b > 999) {
            for (int d1 = 1; d1 <= 9; d1 += 2) {
                for (int d2 = 0; d2 <= 9; d2++) {
                    int x = 1000 * d1 + 100 * d2 + 10 * d2 + d1;
                    if (x >= a && x <= b && isPrime(x)) {
                        primePalindromes.add(x);
                    }
                }
            }
        }
        // 5 digit
        if (a < 100000 && b > 9999) {
            for (int d1 = 1; d1 <= 9; d1 += 2) {
                for (int d2 = 0; d2 <= 9; d2++) {
                    for (int d3 = 0; d3 <= 9; d3++) {
                        int x = 10000 * d1 + 1000 * d2 + 100 * d3 + 10 * d2 + d1;
                        if (x >= a && x <= b && isPrime(x)) {
                            primePalindromes.add(x);
                        }
                    }
                }
            }
        }
        // 6 digit
        if (a < 1000000 && b > 99999) {
            for (int d1 = 1; d1 <= 9; d1 += 2) {
                for (int d2 = 0; d2 <= 9; d2++) {
                    for (int d3 = 0; d3 <= 9; d3++) {
                        int x = 100000 * d1 + 10000 * d2 + 1000 * d3 + 100 * d3 + 10 * d2 + d1;
                        if (x >= a && x <= b && isPrime(x)) {
                            primePalindromes.add(x);
                        }
                    }
                }
            }
        }
        // 7 digit
        if (a < 10000000 && b > 999999) {
            for (int d1 = 1; d1 <= 9; d1 += 2) {
                for (int d2 = 0; d2 <= 9; d2++) {
                    for (int d3 = 0; d3 <= 9; d3++) {
                        for (int d4 = 0; d4 <= 9; d4++) {
                            int x = 1000000 * d1 + 100000 * d2 + 10000 * d3 + 1000 * d4 + 100 * d3 + 10 * d2 + d1;
                            if (x >= a && x <= b && isPrime(x)) {
                                primePalindromes.add(x);
                            }
                        }
                    }
                }
            }
        }
        // 8 digit
        if (a < 10000000 && b > 999999) {
            for (int d1 = 1; d1 <= 9; d1 += 2) {
                for (int d2 = 0; d2 <= 9; d2++) {
                    for (int d3 = 0; d3 <= 9; d3++) {
                        for (int d4 = 0; d4 <= 9; d4++) {
                            int x = 10000000 * d1 + 1000000 * d2 + 100000 * d3 + 10000 * d4 + 1000 * d4 + 100 * d3 + 10 * d2 + d1;
                            if (x >= a && x <= b && isPrime(x)) {
                                primePalindromes.add(x);
                            }
                        }
                    }
                }
            }
        }
    }

    private static boolean isPrime(int x) {
        if (x % 2 == 0) {
            return false;
        }
        for (int i = 3; i * i <= x; i += 2) {
            if(x % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void writeFile() throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter("pprime.out"));
        for (Integer primePalindrome : primePalindromes) {
            text.write(primePalindrome + "");
            text.newLine();
        }
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        allPalindromes();
        writeFile();
    }

}
