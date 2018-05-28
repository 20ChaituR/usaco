/*
 * Created by cravuri on 1/21/18
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Sprinklers2 {

    int N;
    boolean[][] field;

    final int MOD = (int) (1e9) + 7;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("sprinklers.in"));
        N = sc.nextInt();
        field = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            field[a][b] = true;
        }
    }

    void solve() throws IOException {
        boolean[][] up = new boolean[N][N];
        boolean[][] down = new boolean[N][N];
        for (int i1 = 0; i1 < N; i1++) {
            for (int j1 = 0; j1 < N; j1++) {
                for (int i2 = i1; i2 < N; i2++) {
                    for (int j2 = j1; j2 < N; j2++) {
                        if (field[i2][j2]) {
                            up[i1][j1] = true;
                        }
                    }
                }
                for (int i2 = 0; i2 <= i1; i2++) {
                    for (int j2 = 0; j2 <= j1; j2++) {
                        if (field[i2][j2]) {
                            down[i1][j1] = true;
                        }
                    }
                }
            }
        }
        int total = 0;
        for (int i1 = 0; i1 < N; i1++) {
            for (int j1 = 0; j1 < N; j1++) {
                if (!down[i1][j1]) {
                    continue;
                }
                for (int i2 = i1 + 1; i2 < N; i2++) {
                    for (int j2 = j1 + 1; j2 < N; j2++) {
                        if (up[i2][j2]) {
                            total++;
                            total %= MOD;
                        }
                    }
                }
            }
        }
        PrintWriter pw = new PrintWriter(new FileWriter("sprinklers.out"));
        pw.println(total);
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        Sprinklers2 prob = new Sprinklers2();
        prob.readFile();
        prob.solve();
    }

}
