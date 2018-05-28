/*
ID: cravuri
LANG: JAVA
TASK: kimbits
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

class kimbits {

    int N, L;
    long I;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("kimbits.in"));
        N = sc.nextInt();
        L = sc.nextInt();
        I = sc.nextLong();
    }

    void solve() throws IOException {
        long[][] combos = new long[32][32];
        for (int i = 0; i <= N; i++) {
            combos[i][0] = 1;
            combos[0][i] = 1;
        }
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= L; j++) {
                combos[i][j] = combos[i - 1][j] + combos[i - 1][j - 1];
            }
        }
        char[] ans = new char[N];
        int bl = L;
        for (int i = 0; i < N; i++) {
            int len = N - i;
            long c = combos[len - 1][bl];
            if (c < I) {
                ans[i] = '1';
                bl--;
                I -= c;
            } else ans[i] = '0';
        }
        PrintWriter pw = new PrintWriter(new FileWriter("kimbits.out"));
        pw.println(new String(ans));
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        kimbits prob = new kimbits();
        prob.readFile();
        prob.solve();
    }

}
