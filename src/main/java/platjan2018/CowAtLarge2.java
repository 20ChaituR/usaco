/*
 * Created by cravuri on 1/21/18
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class CowAtLarge2 {

    int N;
    List<Integer>[] adj;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("atlarge.in"));
        N = sc.nextInt();
        adj = new List[N];
        for (int i = 0; i < N; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            adj[a].add(b);
            adj[b].add(a);
        }
    }

    void solve() throws IOException {
        Set<Integer> leaves = new HashSet<>();
        for (int i = 0; i < adj.length; i++) {
            if (adj[i].size() == 1) {
                leaves.add(i);
            }
        }
        PrintWriter pw = new PrintWriter(new FileWriter("atlarge.out"));
        for (int i = 0; i < N; i++) {
            if (leaves.contains(i)) {
                pw.println(1);
            } else {
                pw.println(leaves.size());
            }
        }
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        CowAtLarge2 prob = new CowAtLarge2();
        prob.readFile();
        prob.solve();
    }

}
