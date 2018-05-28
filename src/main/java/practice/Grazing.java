/*
 * Created by cravuri on 5/25/18
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Grazing {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int[][] pasture = new int[N][M];
        List<int[]> vals = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                pasture[i][j] = sc.nextInt();
                vals.add(new int[] {pasture[i][j], i, j});
            }
        }
        Collections.sort(vals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0] - o1[0];
            }
        });
        int sum = 0;
        boolean[][] vis = new boolean[N][M];
        int[][] dir = {{0,1},{0,-1},{1,0},{-1,0}};
        for (int[] loc : vals) {
            vis[loc[1]][loc[2]] = true;
            sum += pasture[loc[1]][loc[2]];
            for (int[] d : dir) {
                int ni = loc[1] + d[0];
                int nj = loc[2] + d[1];
                if (ni < 0 || nj < 0 || ni >= N || nj >= M) {
                    continue;
                }
                if (vis[ni][nj]) {
                    sum += pasture[ni][nj];
                }
            }
        }
        System.out.println(sum);
    }

}
