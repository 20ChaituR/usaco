/*
 * Created by cravuri on 1/28/18
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BellmanFord {

    int N, M;
    int[][] edges;
    int src = 0;
    final int INF = (int) 1e9;

    void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(text.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new int[M][3];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(text.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            edges[i] = new int[]{a, b, c}; // node 1 ---> node 2, weight of edge
        }
    }

    int[] solve() {
        int[] dist = new int[N];
        for (int i = 0; i < N; i++) {
            dist[i] = INF;
        }
        dist[src] = 0;
        for (int i = 0; i < N - 1; i++) {
            for (int j = 0; j < M; j++) {
                if (dist[edges[j][1]] > dist[edges[j][0]] + edges[j][2]) {
                    dist[edges[j][1]] = dist[edges[j][0]] + edges[j][2];
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) throws IOException {
        BellmanFord prob = new BellmanFord();
        prob.readFile();
        System.out.println(Arrays.toString(prob.solve()));
    }

}
