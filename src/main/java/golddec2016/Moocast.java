import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Moocast {

    int N;
    int[][] cows;
    boolean[] vis;
    boolean[][] adj;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("moocast.in"));
        N = sc.nextInt();
        cows = new int[N][2];
        for (int i = 0; i < N; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            cows[i][0] = x;
            cows[i][1] = y;
        }
    }

    int dist(int x1, int y1, int x2, int y2) {
        return (x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2);
    }

    void flood(int cur) {
        if (vis[cur]) {
            return;
        }
        vis[cur] = true;
        for (int i = 0; i < adj[cur].length; i++) {
            if (!vis[i] && adj[cur][i]) {
                flood(i);
            }
        }
    }

    boolean isPossible(int x) {
        adj = new boolean[cows.length][cows.length];
        for (int i = 0; i < adj.length; i++) {
            for (int j = 0; j < adj[0].length; j++) {
                if (dist(cows[i][0], cows[i][1], cows[j][0], cows[j][1]) <= x) {
                    adj[i][j] = true;
                }
            }
        }
        vis = new boolean[adj.length];
        flood(0);
        for (int i = 0; i < vis.length; i++) {
            if (!vis[i]) {
                return false;
            }
        }
        return true;
    }

    void solve() throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("moocast.out"));
        int l = 0;
        int r = 1250000001;
        while (l <= r) {
            int m = (l + r) / 2;
            if (isPossible(m)) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        pw.println(l);
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        Moocast prob = new Moocast();
        prob.readFile();
        prob.solve();
    }

}
