/*
ID: cravuri
LANG: JAVA
TASK: sort3
*/

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

class sort3 {

    static int[] d;
    static int[] sorted;
    static int n;
    static int times;

    public static void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("sort3.in"));
        n = sc.nextInt();
        d = new int[n];
        sorted = new int[n];
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            d[i] = x;
            sorted[i] = x;
        }
        Arrays.sort(sorted);
    }

    public static void numExchanges() {
        for (int i = 0; i < n; i++) {
            if (sorted[i] != d[i]) {
                for (int j = i + 1; j < n; ++j) {
                    if (d[j] == sorted[i] && sorted[j] == d[i]) {
                        times++;
                        swap(i, j);
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (sorted[i] != d[i] && d[i] == 1) {
                times += 2;
            }
        }
    }

    private static void swap(int i, int j) {
        int temp = d[i];
        d[i] = d[j];
        d[j] = temp;
    }

    public static void writeFile() throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter("sort3.out"));
        text.write(times + "");
        text.newLine();
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        numExchanges();
        writeFile();
    }

}
