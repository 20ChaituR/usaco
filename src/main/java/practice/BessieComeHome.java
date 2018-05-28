/*
ID: cravuri
LANG: JAVA
TASK: comehome
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

class comehome {

    final int INF = (int) 1e9;
    int P;
    int[][] adj = new int[52][52];

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("comehome.in"));
        P = sc.nextInt();
        for (int i = 0; i < 52; i++) {
            for (int j = 0; j < 52; j++) {
                adj[i][j] = i == j ? 0 : INF;
            }
        }
        for (int i = 0; i < P; i++) {
            int a = id(sc.next().charAt(0));
            int b = id(sc.next().charAt(0));
            int c = sc.nextInt();
            adj[a][b] = Math.min(c, adj[a][b]);
            adj[b][a] = Math.min(c, adj[b][a]);
        }
    }

    int id(char c) {
        if (c >= 'a' && c <= 'z') {
            return c - 'a';
        } else {
            return c - 'A' + 26;
        }
    }

    void solve() throws FileNotFoundException {
        PrintWriter pr = new PrintWriter("comehome.out");
        for (int k = 0; k < 52; k++) {
            for (int i = 0; i < 52; i++) {
                for (int j = 0; j < 52; j++) {
                    adj[i][j] = Math.min(adj[i][k] + adj[k][j], adj[i][j]);
                }
            }
        }
        int x = 26;
        for (int i = 26; i < 51; i++) {
            if (adj[i][51] < adj[x][51]) {
                x = i;
            }
        }
        char c = (char) (x - 26 + 'A');
        pr.print(c + " ");
        pr.println(adj[x][51]);
        pr.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        comehome prob = new comehome();
        prob.readFile();
        prob.solve();
    }

}
