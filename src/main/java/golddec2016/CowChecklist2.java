import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class CowChecklist2 {

    int H, G;
    int[][] h;
    int[][] g;
    PriorityQueue<State> pq = new PriorityQueue<>();

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("checklist.in"));
        H = sc.nextInt();
        G = sc.nextInt();
        h = new int[H][2];
        g = new int[G][2];
        for (int i = 0; i < H; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            h[i][0] = x;
            h[i][1] = y;
        }
        for (int i = 0; i < G; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            g[i][0] = x;
            g[i][1] = y;
        }
    }

    int dist(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }

    void addNext(State curState) {
        if (curState.g == G) {
            if (curState.h < H) {
                // cur == 0 ? (h - 1 --> h) : (g - 1 --> h)
                int c = curState.cur == 0 ? dist(h[curState.h - 1], h[curState.h]) : dist(g[curState.g - 1], h[curState.h]);
                pq.add(new State(curState.h + 1, curState.g, 0, curState.cost + c));
            }
        } else {
            if (curState.h == H - 1) {
                // cur == 0 ? (h - 1 --> g) : (g - 1 --> g)
                int c = curState.cur == 0 ? dist(h[curState.h - 1], g[curState.g]) : dist(g[curState.g - 1], g[curState.g]);
                pq.add(new State(curState.h, curState.g + 1, 1, curState.cost + c));
            } else if (curState.h < H - 1) {
                // cur == 0 ? (h - 1 --> h) : (g - 1 --> h)
                int c = curState.cur == 0 ? dist(h[curState.h - 1], h[curState.h]) : dist(g[curState.g - 1], h[curState.h]);
                pq.add(new State(curState.h + 1, curState.g, 0, curState.cost + c));
                // cur == 0 ? (h - 1 --> g) : (g - 1 --> g)
                c = curState.cur == 0 ? dist(h[curState.h - 1], g[curState.g]) : dist(g[curState.g - 1], g[curState.g]);
                pq.add(new State(curState.h, curState.g + 1, 1, curState.cost + c));
            }
        }
    }

    void dijkstra() throws IOException {
        int[][][] dis = new int[H + 1][G + 1][2];
        for (int i = 0; i <= H; i++) {
            for (int j = 0; j <= G; j++) {
                dis[i][j][0] = -1;
                dis[i][j][1] = -1;
            }
        }
        boolean[][][] vis = new boolean[H + 1][G + 1][2];
        pq.add(new State(1, 0, 0, 0));
        while (!pq.isEmpty()) {
            State curState = pq.remove();
            if (!(curState.h <= H && curState.g <= G)) {
                continue;
            }
            if (vis[curState.h][curState.g][curState.cur]) {
                continue;
            }
            dis[curState.h][curState.g][curState.cur] = curState.cost;
            vis[curState.h][curState.g][curState.cur] = true;
            addNext(curState);
        }
        PrintWriter pw = new PrintWriter(new FileWriter("checklist.out"));
        pw.println(dis[H][G][0]);
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        CowChecklist2 prob = new CowChecklist2();
        prob.readFile();
        prob.dijkstra();
    }

    class State implements Comparable<State>, Comparator<State> {
        int h;
        int g;
        int cur;
        int cost;

        public State(int h, int g, int cur, int cost) {
            this.h = h;
            this.g = g;
            this.cur = cur;
            this.cost = cost;
        }

        @Override
        public int compareTo(State o) {
            return this.cost - o.cost;
        }

        @Override
        public int compare(State o1, State o2) {
            return o1.cost - o2.cost;
        }
    }

}
