import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PrimMST {

    static final int INF = (int) 1e9;
    static int[][] graph;

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(text.readLine());
        int n = Integer.parseInt(st.nextToken());
        graph = new int[n][n];
        int x = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    graph[i][j] = INF;
                } else {
                    graph[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < x; i++) {
            st = new StringTokenizer(text.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken());
            graph[a][b] = c;
            graph[b][a] = c;
        }
    }

    public static void minSpanTree() {
        int cost = 0;
        int[] edgeWeight = new int[graph.length];
        edgeWeight[0] = 0;
        for (int i = 0; i < edgeWeight.length; i++) {
            edgeWeight[i] = graph[0][i];
        }
        for (int i = 1; i < edgeWeight.length; i++) {
            int minEdge = INF;
            int index = -1;
            for (int j = 0; j < graph.length; j++) {
                if (edgeWeight[j] != 0 && edgeWeight[j] < minEdge) {
                    minEdge = edgeWeight[j];
                    index = j;
                }
            }
            edgeWeight[index] = 0;
            cost += minEdge;
            for (int j = 0; j < edgeWeight.length; j++) {
                if (edgeWeight[j] != 0) {
                    edgeWeight[j] = Math.min(edgeWeight[j], graph[index][j]);
                }
            }
        }
        System.out.println(cost);
    }

    public static void main(String[] args) throws IOException {
        readFile();
        minSpanTree();
    }

}
