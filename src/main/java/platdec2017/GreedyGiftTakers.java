/*
 * Created by cravuri on 12/17/17
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class GreedyGiftTakers {

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
        int x = N;
        for (int i = 0; i < N; i++) {
            if (i >= x) {
                break;
            }
            if (N - cows[i] < x) {
                boolean possible = false;
                for (int j = 1; j + i < x; j++) {
                    if (cows[i + j] >= cows[i] + j - 1) {
                        possible = true;
                        break;
                    }
                }
                if (possible) {
                    x = N - cows[i];
                }
            }
        }
        PrintWriter pw = new PrintWriter("greedy.out");
        pw.println(N - x);
        pw.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        GreedyGiftTakers prob = new GreedyGiftTakers();
        prob.readFile();
        prob.solve();
    }
}
