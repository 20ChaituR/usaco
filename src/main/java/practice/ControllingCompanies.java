/*
ID: cravuri
LANG: JAVA
TASK: concom
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

class concom {

    private static boolean[] visited = new boolean[102];
    private static int maxN = 0;
    private static int[] control = new int[102];
    private static int[][] percentages = new int[102][102];
    private static String solution;

    public static void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("concom.in"));
        int n = sc.nextInt();
        for (int x = 0; x < n; x++) {
            int i = sc.nextInt();
            int j = sc.nextInt();
            int p = sc.nextInt();
            if (i > maxN) {
                maxN = i;
            }
            if (j > maxN) {
                maxN = j;
            }
            percentages[i][j] = p;
        }
    }

    private static void solve() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= maxN; i++) {
            Arrays.fill(visited, 0, maxN + 1, false);
            Arrays.fill(control, 0, maxN + 1, 0);
            search(i);
            for (int t = 1; t <= maxN; t++) {
                if (control[t] > 50 && t != i) {
                    sb.append(i).append(" ").append(t).append("\n");
                }
            }
        }
        solution = sb.toString();
    }

    private static void search(int index) {
        if (visited[index]) {
            return;
        }
        visited[index] = true;
        for (int i = 1; i <= maxN; i++) {
            control[i] += percentages[index][i];
            if (control[i] > 50) {
                search(i);
            }
        }
    }

    public static void writeFile() throws IOException {
        PrintWriter pr = new PrintWriter(new FileWriter("concom.out"));
        pr.print(solution);
        pr.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        solve();
        writeFile();
    }

}
