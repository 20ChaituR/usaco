import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CowChecklist {

    int H, G;
    int[][] h;
    int[][] g;

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

    void solve() throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("checklist.out"));
        int[][][] dp = new int[H + 1][G + 1][2];
        dp[1][0][0] = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i == 1) {
                    if (j > 0) {
                        dp[i][j][1] = dp[i][j - 1][0];
                    }
                } else if (i == H) {
                    if (j == G) {
                        // |
                    }
                } else {
                    if (j == 0) {
                        // |
                    } else {
                        // -|
                    }
                }
            }
        }
        pw.println(dp[H - 1][G - 1][0]);
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        CowChecklist prob = new CowChecklist();
        prob.readFile();
        prob.solve();
    }

}
