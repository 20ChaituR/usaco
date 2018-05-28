/*
 * Created by cravuri on 1/21/18
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Lifeguards3 {

    int N, K;
    int[][] ranges;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("lifeguards.in"));
        N = sc.nextInt();
        K = sc.nextInt();
        ranges = new int[N][2];
        for (int i = 0; i < N; i++) {
            ranges[i] = new int[]{sc.nextInt(), sc.nextInt()};
        }
    }

    void solve() throws IOException {
        Arrays.sort(ranges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });
        int[][] dp = new int[N][N + 1]; // up until range i, using j ranges, max timeP, using range i
        int[][] max = new int[N][N + 1]; // max of dp[0..i][j]
        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= i + 1; j++) {
                dp[i][j] = ranges[i][1] - ranges[i][0];
                for (int k = i - 1; k >= 0; k--) {
                    if (ranges[k][1] < ranges[i][0]) {
                        dp[i][j] = Math.max(dp[i][j], max[k][j - 1] + (ranges[i][1] - ranges[i][0]));
                        break;
                    }
                    dp[i][j] = Math.max(dp[i][j], dp[k][j - 1] + (ranges[i][1] - ranges[k][1]));
                }
                if (i > 0) {
                    max[i][j] = Math.max(max[i - 1][j], dp[i][j]);
                } else {
                    max[i][j] = dp[i][j];
                }
            }
        }
        PrintWriter pw = new PrintWriter(new FileWriter("lifeguards.out"));
        pw.println(max[N - 1][N - K]);
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        Lifeguards3 prob = new Lifeguards3();
        prob.readFile();
        prob.solve();
    }

}
