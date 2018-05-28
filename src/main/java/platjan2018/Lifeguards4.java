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

public class Lifeguards4 {

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
        int[] dpOld = new int[N];
        int[] dpNew = new int[N];
        int[] maxOld = new int[N];
        int[] maxNew = new int[N];
        for (int j = 1; j <= N - K; j++) {
            for (int i = 0; i < N; i++) {
                if (j > i + 1) {
                    continue;
                }
                dpNew[i] = ranges[i][1] - ranges[i][0];
                for (int k = i - 1; k >= 0; k--) {
                    if (ranges[k][1] < ranges[i][0]) {
                        dpNew[i] = Math.max(dpNew[i], maxOld[k] + (ranges[i][1] - ranges[i][0]));
                        break;
                    }
                    dpNew[i] = Math.max(dpNew[i], dpOld[k] + (ranges[i][1] - ranges[k][1]));
                }
                if (i > 0) {
                    maxNew[i] = Math.max(maxNew[i - 1], dpNew[i]);
                } else {
                    maxNew[i] = dpNew[i];
                }
            }
            for (int i = 0; i < N; i++) {
                dpOld[i] = dpNew[i];
                maxOld[i] = maxNew[i];
                dpNew[i] = 0;
                maxNew[i] = 0;
            }
        }
        PrintWriter pw = new PrintWriter(new FileWriter("lifeguards.out"));
        pw.println(maxOld[N - 1]);
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        Lifeguards4 prob = new Lifeguards4();
        prob.readFile();
        prob.solve();
    }

}
