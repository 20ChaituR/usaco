/*
ID: cravuri
LANG: JAVA
TASK: humble
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class humble {

    int K, N;
    int[] S;
    int[] M;
    List<Long> H = new ArrayList<Long>();

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("humble.in"));
        K = sc.nextInt();
        N = sc.nextInt();
        S = new int[K];
        M = new int[K];
        for (int i = 0; i < K; i++) {
            S[i] = sc.nextInt();
        }
    }

    void solve() throws IOException {
        H.add(1L);
        int pos = 0;
        while (pos < N + 1) {
            long next = Long.MAX_VALUE;
            List<Integer> which = new ArrayList<Integer>();
            for (int i = 0; i < K; i++) {
                long sol = S[i] * H.get(M[i]);
                if (sol > H.get(pos) && sol <= next) {
                    if (sol < next) {
                        next = sol;
                        which.clear();
                        which.add(0, i);
                    } else if (sol == next) {
                        which.add(i);
                    }
                }
            }
            H.add(next);
            for (int i = 0; i < which.size(); i++) {
                M[which.get(i)]++;
            }
            pos++;
        }
        PrintWriter pw = new PrintWriter(new FileWriter("humble.out"));
        pw.println(H.get(N));
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        humble prob = new humble();
        prob.readFile();
        prob.solve();
    }

}
