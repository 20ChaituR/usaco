/*
 * Created by cravuri on 12/17/17
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class GreedyGiftTakers2 {

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
        boolean[] possible = new boolean[N];
        int cur = 0;
        possible[cur] = true;
//        int max = 0;
        for (int n = 0; n < 1000000; n++) {
            for (int i = 1; i < N - cows[cur]; i++) {
                order[i - 1] = order[i];
            }
            order[N - cows[cur] - 1] = cur;
            cur = order[0];
//            if (cur > max) {
//                max = cur;
//            }
            possible[cur] = true;
        }
        int n = 0;
        for (int i = 0; i < N; i++) {
            if (!possible[i]) {
                n++;
            }
        }
        PrintWriter pw = new PrintWriter("greedy.out");
        pw.println(n);
        pw.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        GreedyGiftTakers2 prob = new GreedyGiftTakers2();
        prob.readFile();
        prob.solve();
    }

}
