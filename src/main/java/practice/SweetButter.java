/*
ID: cravuri
LANG: JAVA
TASK: butter
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

class butter {

    final int INF = 1000;

    int N, P, C;
    int[] cows;
    int[][] pastures;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("butter.in"));
        N = sc.nextInt();
        P = sc.nextInt();
        C = sc.nextInt();
        cows = new int[N];
        pastures = new int[P][P];
        for (int i = 0; i < N; i++) {
            cows[i] = sc.nextInt() - 1;
        }
        for (int i = 0; i < P; i++) {
            for (int j = 0; j < P; j++) {
                pastures[i][j] = i == j ? 0 : INF;
            }
        }
        for (int i = 0; i < C; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            int c = sc.nextInt();
            pastures[a][b] = c;
            pastures[b][a] = c;
        }
    }

    int[] dijkstra(int S) {
        int[] dis = new int[P];
        for (int i = 0; i < P; i++) {
            dis[i] = -1;
        }
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        boolean[] vis = new boolean[P];
        pq.add(new Pair(0, S));
        int count = 0;
        while (count < P && !pq.isEmpty()) {
            Pair next = pq.remove();
            if (vis[next.second]) {
                continue;
            }
            dis[next.second] = next.first;
            vis[next.second] = true;
            for (int i = 0; i < pastures[next.second].length; i++) {
                if (pastures[next.second][i] != INF) {
                    pq.add(new Pair(pastures[next.second][i] + next.first, i));
                }
            }
            count++;
        }
        return dis;
    }

    void solve2() throws IOException {
        int min = Integer.MAX_VALUE;
        for (int s = 0; s < pastures.length; s++) {
            int[] dist = dijkstra(s);
            int sum = 0;
            for (int c : cows) {
                sum += dist[c];
            }
            min = Math.min(min, sum);
        }
        PrintWriter pw = new PrintWriter(new FileWriter("butter.out"));
        pw.println(min);
        pw.close();
    }

//    void solve() throws IOException {
//        for (int k = 0; k < pastures.length; k++) {
//            for (int i = 0; i < pastures.length; i++) {
//                for (int j = 0; j < pastures.length; j++) {
//                    pastures[i][j] = Math.min(pastures[i][j], pastures[i][k] + pastures[k][j]);
//                }
//            }
//        }
//        int min = Integer.MAX_VALUE;
//        for (int s = 0; s < pastures.length; s++) {
//            int sum = 0;
//            for (int c : cows) {
//                sum += pastures[c][s];
//            }
//            min = Math.min(min, sum);
//        }
//        PrintWriter pw = new PrintWriter(new FileWriter("butter.out"));
//        pw.println(min);
//        pw.close();
//    }

    public static void main(String[] args) throws IOException {
        butter prob = new butter();
        prob.readFile();
        prob.solve2();
    }

    class Pair implements Comparator<Pair>, Comparable<Pair> {
        int first;
        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Pair o) {
            return o.first == first ? second - o.second : first - o.first;
        }

        @Override
        public int compare(Pair o1, Pair o2) {
            return o1.first == o2.first ? o1.second - o2.second : o1.first - o2.first;
        }
    }

}
