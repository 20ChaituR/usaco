/*
 * Created by cravuri on 12/17/17
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class GreedyGiftTakers4 {

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

    boolean possible2(int c) {
        if (c == 0) {
            return true;
        }
        int end = c;
        int[] nCows = new int[c];
        for (int i = 0; i < c; i++) {
            nCows[i] = cows[i];
        }
        Arrays.sort(nCows);
        for (int i = 0; i < nCows.length; i++) {
            if (N - nCows[i] > end) {
                end--;
            } else {
                break;
            }
        }
        return end == 0;
    }

    boolean possible(int c) {
        LinkedList<Integer> vals = new LinkedList<>();
        for (int i = 0; i < c; i++) {
            vals.add(cows[i]);
        }
        while (true) {
            ListIterator<Integer> it = vals.listIterator();
            boolean found = false;
            while (it.hasNext()) {
                int x = it.next();
                if (N - x > vals.size()) {
                    it.remove();
                    found = true;
                    break;
                }
            }
            if (!found) {
                break;
            }
        }
        return vals.size() == 0;
//        int i = 0;
//        int end = c;
//        while (true) {
//            if (i >= c) {
//                break;
//            }
//            if (N - cows[i] > end) {
//                i = 0;
//                end--;
//            } else {
//                i++;
//            }
//        }
//        return end == 0;
    }

    void solve() throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("greedy.out"));
        int l = 0;
        int r = N;
        while (l <= r) {
            int m = (l + r) / 2;
            if (!possible2(m)) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        pw.println(N - l);
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        GreedyGiftTakers4 prob = new GreedyGiftTakers4();
        prob.readFile();
        prob.solve();
    }

}
