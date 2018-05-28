/*
 * Created by cravuri on 12/17/17
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class StandingOutFromTheHerd {

    int N;
    String[] cows;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("standingout.in"));
        N = sc.nextInt();
        cows = new String[N];
        for (int i = 0; i < N; i++) {
            cows[i] = sc.next();
        }
    }

    void solve() throws FileNotFoundException {
        HashMap<String, Integer> str = new HashMap<>();
        Set<String>[] substrings = new Set[N];
        for (int i = 0; i < cows.length; i++) {
            substrings[i] = new HashSet<>();
            for (int j = 0; j < cows[i].length(); j++) {
                for (int k = j + 1; k <= cows[i].length(); k++) {
                    String sub = cows[i].substring(j, k);
                    substrings[i].add(sub);
                }
            }
            for (String sub : substrings[i]) {
                if (str.containsKey(sub)) {
                    str.put(sub, str.get(sub) + 1);
                } else {
                    str.put(sub, 1);
                }
            }
        }
        int[] unique = new int[N];
        for (int i = 0; i < cows.length; i++) {
            for (String sub : substrings[i]) {
                if (str.get(sub) == 1) {
                    unique[i]++;
                }
            }
        }
        PrintWriter pw = new PrintWriter("standingout.out");
        for (int i = 0; i < unique.length; i++) {
            pw.println(unique[i]);
        }
        pw.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        StandingOutFromTheHerd prob = new StandingOutFromTheHerd();
        prob.readFile();
        prob.solve();
    }

}
