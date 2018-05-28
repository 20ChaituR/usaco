/*
ID: cravuri
LANG: JAVA
TASK: holstein
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

class holstein {

    static int N;
    static int[] v = new int[25];
    static int M;
    static int[][] f = new int[15][25];
    static int[] s = new int[15];
    static int[] p = new int[25];
    static int minans = Integer.MAX_VALUE;
    static int[] hash = new int[15];

    static void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("holstein.in"));
        N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            v[i] = sc.nextInt();
        }
        M = sc.nextInt();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                f[i][j] = sc.nextInt();
            }
        }
    }

    static void DFS(int n) {
        boolean ok = true;
        for (int i = 0; i < N; i++) {
            if (p[i] < v[i]) {
                ok = false;
                break;
            }
        }
        if (ok) {
            int ans = 0;
            for (int i = 0; i < n; i++) {
                ans += s[i];
            }
            if (ans <= minans) {
                minans = ans;
                Arrays.fill(hash, 0, hash.length, 0);
                for (int i = 0; i < M; i++) {
                    if (s[i] == 1) {
                        hash[i] = 1;
                    }
                }
            }
            return;
        }
        if (n == M) {
            return;
        }
        for (s[n] = 0; s[n] <= 1; s[n]++) {
            for (int i = 0; i < N; i++) {
                p[i] += f[n][i] * s[n];
            }
            DFS(n + 1);
            for (int i = 0; i < N; i ++) {
                p[i] -= f[n][i] * s[n];
            }
        }
    }

    static void writeFile() throws IOException {
        PrintWriter pr = new PrintWriter(new FileWriter("holstein.out"));
        pr.print(minans);
        for (int i = 0; i < M; i++) {
            if (hash[i] != 0) {
                pr.print(" " + (i + 1));
            }
        }
        pr.println();
        pr.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        DFS(0);
        writeFile();
    }
}
