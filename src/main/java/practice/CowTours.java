/*
ID: cravuri
LANG: JAVA
TASK: cowtour
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class cowtour {

    final static int INF = 1000000000;
    static int N;
    static double minD = Integer.MAX_VALUE;
    static double[][] points;
    static double[][] dist;
    static double[] farthest;
    static double[] diameter;
    static Set<Integer> field;
    static boolean[] visited;

    public static void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("cowtour.in"));
        N = sc.nextInt();
        points = new double[N][2];
        dist = new double[N][N];
        farthest = new double[N];
        diameter = new double[N];
        for (int i = 0; i < N; i++) {
            double x = (double) sc.nextInt();
            double y = (double) sc.nextInt();
            points[i] = new double[]{x, y};
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i != j) {
                    dist[i][j] = INF;
                } else {
                    dist[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            String ln = sc.next();
            for (int j = 0; j < ln.length(); j++) {
                if (ln.charAt(j) == '1') {
                    dist[i][j] = pDist(points[i], points[j]);
                }
            }
        }
    }

    public static double pDist(double[] p1, double[] p2) {
        return Math.sqrt((p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]));
    }

    public static void flood(int v) {
        visited[v] = true;
        field.add(v);
        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }
            if (dist[v][i] == INF) {
                continue;
            }
            flood(i);
        }
    }

    public static void solve() throws FileNotFoundException {
        PrintWriter pr = new PrintWriter("cowtour.out");
        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (dist[i][j] != INF) {
                    farthest[i] = Math.max(farthest[i], dist[i][j]);
                }
            }
        }
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                field = new HashSet<Integer>();
                flood(i);
                double d = -1;
                for (int v1 : field) {
                    for (int v2 : field) {
                        d = Math.max(dist[v1][v2], d);
                    }
                }
                for (int v : field) {
                    diameter[v] = d;
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (dist[i][j] == INF) {
                    double d = farthest[i] + pDist(points[i], points[j]) + farthest[j];
                    d = Math.max(d, Math.max(diameter[i], diameter[j]));
                    minD = Math.min(minD, d);
                }
            }
        }
        double x = (Math.round(minD * 1000000.0) / 1000000.0);
        if (Math.round(x) == x) {
            String s = x + "";
            for (int i = 0; i < 5; i++) {
                s += "0";
            }
            pr.println(s);
            pr.close();
        } else {
            pr.println(Math.round(minD * 1000000.0) / 1000000.0);
            pr.close();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        readFile();
        solve();
    }

}

