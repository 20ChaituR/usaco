import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Dijkstra1 {

    static int[][] graph;
    static int source;
    static int[] dist;
    static int[] prev;
    static Set<Integer> Q = new HashSet<Integer>();

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(text.readLine());
        int v = Integer.parseInt(st.nextToken());
        graph = new int[v][v];
        dist = new int[v];
        prev = new int[v];
        int e = Integer.parseInt(st.nextToken());
        source = Integer.parseInt(st.nextToken()) - 1;
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++) {
                graph[i][j] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(text.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph[a - 1][b - 1] = c;
            graph[b - 1][a - 1] = c;
        }
    }

    public static void dijkstraShortestPath() {
        for (int v = 0; v < graph.length; v++) {
            dist[v] = Integer.MAX_VALUE;
            prev[v] = -1;
            Q.add(v);
        }
        dist[source] = 0;

        while (Q.size() > 0) {
            int x = Integer.MAX_VALUE;
            int u = -1;
            for (int i = 0; i < graph.length; i++) {
                if (Q.contains(i)) {
                    if (dist[i] < x) {
                        x = dist[i];
                        u = i;
                    }
                }
            }
            if (u == -1) {
                break;
            }
            Q.remove(u);

            for (int v = 0; v < graph.length; v++) {
                if (graph[u][v] != Integer.MAX_VALUE) {
                    int alt = dist[u] + graph[u][v];
                    if (alt < dist[v]) {
                        dist[v] = alt;
                        prev[v] = u;
                    }
                }
            }
        }
    }

    public static void writeFile() throws IOException {
        BufferedWriter text = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int distance : dist) {
            if (distance == Integer.MAX_VALUE) {
                text.write("-1");
            } else {
                text.write(distance + "");
            }
            text.newLine();
        }
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        dijkstraShortestPath();
        writeFile();
    }

}
