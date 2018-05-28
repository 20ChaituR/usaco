/*
ID: cravuri
LANG: JAVA
TASK: sprime
*/

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class sprime {

    static int n;
    static int[] primes = new int[] {2, 3, 5, 7};
    static List<Integer> superPrimes = new ArrayList<Integer>();

    public static void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("sprime.in"));
        n = sc.nextInt();
    }

    static void findPrimes(int count, int num) {
        if (count == 0) {
            for (int prime : primes) {
                findPrimes(count + 1, num + prime * (int) Math.pow(10, n - 1));
            }
            return;
        }
        if (count == n) {
            superPrimes.add(num);
            return;
        }
        for (int i = 1; i <= 9; i += 2) {
            int x = num + i * (int) Math.pow(10, n - (count + 1));
            if (isPrime(x / (int) Math.pow(10, n - (count + 1)))) {
                findPrimes(count + 1, x);
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
        BufferedWriter text = new BufferedWriter(new FileWriter("sprime.out"));
        for (Integer superPrime : superPrimes) {
            text.write(superPrime + "");
            text.newLine();
        }
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        findPrimes(0, 0);
        writeFile();
    }

}
