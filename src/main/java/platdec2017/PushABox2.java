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

public class PushABox2 {

    int N, M, Q;
    char[][] barn;
    boolean[][] possible;
    int ar, ac, br, bc;
    int[][] dir = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    int[][] queries;
    boolean[][][] bessieReach;
    int[][] bessieDir = {};

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

    boolean canReach(int i, int j, int ai, int aj, int bi, int bj) {
        return false;
    }

    void canReach() {
        bessieReach = new boolean[N][M][12];

    }

    void fillPossible(int ai, int aj, int bi, int bj) {
        if (ai < 0 || ai >= N || aj < 0 || aj >= M) {
            return;
        }
        if (bi < 0 || bi >= N || bj < 0 || bj >= M) {
            return;
        }
        possible[bi][bj] = true;
        for (int d = 0; d < 4; d++) {
            int nai = bi - dir[d][0];
            int naj = bj - dir[d][1];
            if (canReach(nai, naj, ai, aj, bi, bj)) {

            }
        }
    }

    void solve() throws FileNotFoundException {
        boolean[][] vis = new boolean[N][M];
        possible = new boolean[N][M];
        possible[br][bc] = true;
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{ar, ac, br, bc});
        vis[ar][ac] = true;
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
                    if (vis[ai][aj]) {
                        continue;
                    }
                    vis[ai][aj] = true;
                    possible[bi][bj] = true;
                    q.add(new int[]{ai, aj, bi, bj});
                    continue;
                }
                if (vis[ai][aj]) {
                    continue;
                }
                vis[ai][aj] = true;
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
        PushABox2 prob = new PushABox2();
        prob.readFile();
        prob.solve();
    }

}
