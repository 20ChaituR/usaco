/*
ID: cravuri
LANG: JAVA
TASK: money
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

class money {

    static int V;
    static int N;
    static int[] coins;

    public static void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("money.in"));
        V = sc.nextInt();
        N = sc.nextInt();
        coins = new int[V];
        for (int i = 0; i < V; i++) {
            coins[i] = sc.nextInt();
        }
    }

    public static long solve() {
        long[][] dp = new long[V + 1][N + 1];
        dp[0][0] = 1L;
        for (int i = 1; i < dp[0].length; i++) {
            dp[0][i] = 0L;
        }
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (coins[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                }
            }
        }
        return dp[V][N];
    }

    public static void writeFile(long x) throws IOException {
        PrintWriter pr = new PrintWriter(new FileWriter("money.out"));
        pr.println(x);
        pr.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        writeFile(solve());
    }

}
