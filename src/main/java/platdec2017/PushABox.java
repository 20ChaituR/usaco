/*
 * Created by cravuri on 12/17/17
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class PushABox {

    int N, M, Q;
    char[][] barn;
    boolean[][] possible;
    int ar, ac, br, bc;
    int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int[][] queries;

    void readFile() throws IOException {
        BufferedReader b = new BufferedReader(new FileReader("pushabox.in"));
        StringTokenizer st = new StringTokenizer(b.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        barn = new char[N][M];
        possible = new boolean[N][M];
        queries = new int[Q][2];
        for (int i = 0; i < N; i++) {
            barn[i] = b.readLine().toCharArray();
            for (int j = 0; j < barn[i].length; j++) {
                if (barn[i][j] == 'A') {
                    ar = i;
                    ac = j;
                }
                if (barn[i][j] == 'B') {
                    br = i;
                    bc = j;
                }
            }
        }
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(b.readLine());
            queries[i][0] = Integer.parseInt(st.nextToken()) - 1;
            queries[i][1] = Integer.parseInt(st.nextToken()) - 1;
        }
    }

    void solve() throws FileNotFoundException {
        boolean[][][][] vis = new boolean[N][M][N][M];
        possible = new boolean[N][M];
        possible[br][bc] = true;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{ar, ac, br, bc});
        vis[ar][ac][br][bc] = true;
        while (!q.isEmpty()) {
            int[] cur = q.remove();
            for (int d = 0; d < 4; d++) {
                int ai = cur[0] + dir[d][0];
                int aj = cur[1] + dir[d][1];
                if (ai < 0 || ai >= N || aj < 0 || aj >= M) {
                    continue;
                }
                if (barn[ai][aj] == '#') {
                    continue;
                }
                if (ai == cur[2] && aj == cur[3]) {
                    int bi = cur[2] + dir[d][0];
                    int bj = cur[3] + dir[d][1];
                    if (bi < 0 || bi >= N || bj < 0 || bj >= M) {
                        continue;
                    }
                    if (barn[bi][bj] == '#') {
                        continue;
                    }
                    if (vis[ai][aj][bi][bj]) {
                        continue;
                    }
                    vis[ai][aj][bi][bj] = true;
                    possible[bi][bj] = true;
                    q.add(new int[]{ai, aj, bi, bj});
                    continue;
                }
                if (vis[ai][aj][cur[2]][cur[3]]) {
                    continue;
                }
                vis[ai][aj][cur[2]][cur[3]] = true;
                q.add(new int[]{ai, aj, cur[2], cur[3]});
            }
        }
        PrintWriter pw = new PrintWriter("pushabox.out");
        for (int i = 0; i < queries.length; i++) {
            if (possible[queries[i][0]][queries[i][1]]) {
                pw.println("YES");
            } else {
                pw.println("NO");
            }
        }
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        PushABox prob = new PushABox();
        prob.readFile();
        prob.solve();
    }

}
