/*
 * Created by cravuri on 12/17/17
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class GreedyGiftTakers3 {

    int N;
    int[] cows;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("greedy.in"));
        N = sc.nextInt();
        cows = new int[N];
        for (int i = 0; i < cows.length; i++) {
            cows[i] = sc.nextInt();
        }
        sc.close();
    }

    void solve() throws FileNotFoundException {
        int[] order = new int[N];
        for (int i = 0; i < order.length; i++) {
            order[i] = i;
        }
        boolean[] notPossible = new boolean[N];
        boolean[] vis = new boolean[N];
        int max = 0;
        for (int i = 0; i < N; i++) {
//            if (notPossible[i]) {
//                break;
//            }
            int cur = order[0];
            vis[cur] = true;
            if (cows[cur] > max) {
                boolean possible = false;
                for (int j = 1; j < N - cows[cur]; j++) {
                    if (cows[order[j]] >= cows[cur] + j - 1) {
                        possible = true;
                        break;
                    }
                }
                if (possible) {
                    max = cows[cur];
                    for (int j = N - cows[cur]; j < N; j++) {
                        notPossible[order[j]] = true;
                    }
                }
            }
            for (int j = 1; j < N - cows[cur]; j++) {
                order[j - 1] = order[j];
            }
            order[N - cows[cur] - 1] = cur;

        }
        PrintWriter pw = new PrintWriter("greedy.out");
        for (int i = 0; i < notPossible.length; i++) {
            if (!vis[i]) {
                pw.println(N - i);
                break;
            }
        }
        pw.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        GreedyGiftTakers3 prob = new GreedyGiftTakers3();
        prob.readFile();
        prob.solve();
    }

}
