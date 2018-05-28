/*
 * Created by cravuri on 2/25/18
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Slingshot {

    int N, M;

    List<int[]> slingR = new ArrayList<>();
    List<int[]> slingL = new ArrayList<>();
    int[][] queries;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("slingshot.in"));
        N = sc.nextInt();
        M = sc.nextInt();
        for (int i = 0; i < N; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            int c = sc.nextInt();
            if (a < b) {
                slingR.add(new int[]{a, b, c});
            } else {
                slingL.add(new int[]{a, b, c});
            }
        }
        queries = new int[M][2];
        for (int i = 0; i < M; i++) {
            queries[i] = new int[] {sc.nextInt(), sc.nextInt()};
        }
    }

    void solve() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("slingshot.out");
        for (int i = 0; i < queries.length; i++) {
            if (queries[i][1] > queries[i][0]) {
                int min = queries[i][1] - queries[i][0];
                for (int[] sling : slingR) {
                    min = Math.min(min,
                            Math.abs(queries[i][0] - sling[0]) + sling[2] + Math.abs(queries[i][1] - sling[1]));
                }
                pw.println(min);
            } else {
                int min = queries[i][0] - queries[i][1];
                for (int[] sling : slingL) {
                    min = Math.min(min,
                            Math.abs(queries[i][0] - sling[0]) + sling[2] + Math.abs(queries[i][1] - sling[1]));
                }
                pw.println(min);
            }
        }
        pw.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        Slingshot prob = new Slingshot();
        prob.readFile();
        prob.solve();
    }

}
