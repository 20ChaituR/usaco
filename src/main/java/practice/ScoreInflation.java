/*
ID: cravuri
LANG: JAVA
TASK: inflate
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

class inflate {

    int M, N;
    int[] points;
    int[] length;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("inflate.in"));
        M = sc.nextInt();
        N = sc.nextInt();
        points = new int[N];
        length = new int[N];
        for (int i = 0; i < N; i++) {
            points[i] = sc.nextInt();
            length[i] = sc.nextInt();
        }
    }

    void solve2() throws IOException {
        int[] dpOld = new int[M + 1];
        int[] dpNew = new int[M + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < dpNew.length; j++) {
                if (j - length[i] < 0) {
                    dpNew[j] = dpOld[j];
                } else {
                    dpNew[j] = Math.max(dpOld[j], points[i] + dpNew[j - length[i]]);
                }
            }
            for (int j = 0; j < dpOld.length; j++) {
                dpOld[j] = dpNew[j];
                dpNew[j] = 0;
            }
        }
        int max = -1;
        for (int i = 0; i < dpOld.length; i++) {
            max = Math.max(max, dpOld[i]);
        }
        PrintWriter pr = new PrintWriter(new FileWriter("inflate.out"));
        pr.println(max);
        pr.close();
    }

//    void solve() throws IOException {
//        int[][] dp = new int[N + 1][M + 1];
//        for (int i = 1; i < dp.length; i++) {
//            for (int j = 0; j < dp[0].length; j++) {
//                if (j - length[i - 1] < 0) {
//                    dp[i][j] = dp[i - 1][j];
//                } else {
//                    dp[i][j] = Math.max(dp[i - 1][j], points[i - 1] + dp[i][j - length[i - 1]]);
//                }
//            }
//        }
//        int max = -1;
//        for (int i = 0; i < dp[0].length; i++) {
//            max = Math.max(max, dp[N][i]);
//        }
//        PrintWriter pr = new PrintWriter(new FileWriter("inflate.out"));
//        pr.println(max);
//        pr.close();
//    }

    public static void main(String[] args) throws IOException {
        inflate prob = new inflate();
        prob.readFile();
        prob.solve2();
    }

}
