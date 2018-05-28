/*
ID: cravuri
LANG: JAVA
TASK: agrinet
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

class agrinet {

    final static int INF = (int) 1e9;
    static int N;
    static int[][] graph;

    public static void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("agrinet.in"));
        N = sc.nextInt();
        graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                graph[i][j] = sc.nextInt();
            }
        }
    }

    public static void solve() throws IOException {
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
                edgeWeight[j] = Math.min(edgeWeight[j], graph[index][j]);
            }
        }
        PrintWriter pw = new PrintWriter(new FileWriter("agrinet.out"));
        pw.println(cost);
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        solve();
    }

}
