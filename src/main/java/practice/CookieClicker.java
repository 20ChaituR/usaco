package practice;
/*
 * Created by cravuri on 5/24/18
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class CookieClicker {

    int N, T;
    int[] R;
    int[] C;
    int[] D;

    void readFile() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        T = sc.nextInt();
        R = new int[2];
        R[0] = sc.nextInt();
        R[1] = sc.nextInt();
        C = new int[N];
        D = new int[N];
        for (int i = 0; i < N; i++) {
            C[i] = sc.nextInt();
        }
        for (int i = 0; i < N; i++) {
            D[i] = sc.nextInt();
        }
    }

    void solve() {
        int[][] farms = new int[C.length + D.length][2];
        for (int i = 0; i < C.length; i++) {
            farms[i] = new int[]{C[i], 0};
        }
        for (int i = 0; i < D.length; i++) {
            farms[N + i] = new int[] {D[i], 1};
        }
        Arrays.sort(farms, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    if (o1[1] == o2[1]) {
                        return 0;
                    } else {
                        return R[o2[1]]-R[o1[1]];
                    }
                }
                return o1[0]-o2[0];
            }
        });
        int[][] dp = new int[farms.length + 1][2];
        dp[0][0] = T;
        dp[0][1] = 1;
        for (int i = 1; i < dp.length; i++) {
            dp[i][0] = Integer.MAX_VALUE;
            for (int j = i - 1; j >= 0; j--) {
                int notUse = dp[j][0];
                int use = dp[j][0] - T / dp[j][1] + farms[i-1][0] + T / (dp[j][1] + R[farms[i-1][1]]);
                if (use < notUse) {
                    if (use < dp[i][0]) {
                        dp[i][0] = use;
                        dp[i][1] = dp[j][1] + R[farms[i-1][1]];
                    }
                } else {
                    if (notUse < dp[i][0]) {
                        dp[i][0] = notUse;
                        dp[i][1] = dp[j][1];
                    }
                }
            }
        }
        System.out.println(dp[dp.length - 1][0]);
    }

}
