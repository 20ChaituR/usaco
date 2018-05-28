/*
ID: cravuri
LANG: JAVA
TASK: subset
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class subset {

    static int[] a;
    static int n;
    static int[][] ways;

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("subset.in"));
        n = Integer.parseInt(text.readLine());
        a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i + 1;
        }
    }

    public static int findSums() {
        int S = n * (n + 1) / 2;
        if (S % 2 != 0) {
            return 0;
        }
        S = S / 2;
        ways = new int[n][S + 1];
        ways[0][0] = 1;
        for (int i = 1; i < n; i++) {
            for (int s = 0; s <= S; s++) {
                ways[i][s] = ways[i - 1][s];
                if (s - a[i] >= 0) {
                    ways[i][s] += ways[i - 1][s - a[i]];
                }
            }
        }
        return ways[n - 1][S];
    }

    public static void writeFile(int n) throws IOException {
        PrintWriter pr = new PrintWriter(new FileWriter("subset.out"));
        pr.println(n);
        pr.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        writeFile(findSums());
    }

}
