/*
ID: cravuri
LANG: JAVA
TASK: nocows
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

class nocows {

    static int[][] tstore;
    static int N;
    static int K;

    public static void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("nocows.in"));
        N = sc.nextInt();
        K = sc.nextInt();
        tstore = new int[N + 1][K + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= K; j++) {
                tstore[i][j] = -1;
            }
        }
    }

    private static int tree(int a, int b) {
        int sum = 0;
        if (a == 0 || b == 0) {
            return 0;
        }
        if (a == 1) {
            return 1;
        }
        if (tstore[a][b] != -1) {
            return tstore[a][b];
        }
        for (int i = 1; i < a - 1; i++) {
            sum = (sum + tree(i, b - 1) * tree(a - i - 1, b - 1)) % 9901;
        }
        tstore[a][b] = sum;
        return sum;
    }

    public static void writeFile() throws IOException {
        PrintWriter pr = new PrintWriter(new FileWriter("nocows.out"));
        pr.println((9901 + tree(N, K) - tree(N, K - 1)) % 9901);
        pr.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        writeFile();
    }

}
