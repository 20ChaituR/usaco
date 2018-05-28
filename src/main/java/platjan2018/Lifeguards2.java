/*
 * Created by cravuri on 1/21/18
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Lifeguards2 {

    int N, K;
    int[][] ranges;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("lifeguards.in"));
        N = sc.nextInt();
        K = sc.nextInt();
        ranges = new int[N][2];
        for (int i = 0; i < N; i++) {
            ranges[i] = new int[]{sc.nextInt(), sc.nextInt()};
        }
    }

    void solve() throws IOException {
        int maxTime = 0;
        for (int i = 0; i < N - K; i++) {
            int maxCount = 0;
            int maxJ = 0;
            for (int j = 0; j < ranges.length; j++) {
                int x = ranges[j][1] - ranges[j][0];
                if (x > maxCount) {
                    maxCount = x;
                    maxJ = j;
                }
            }
            if (maxCount == 0) {
                break;
            }
            maxTime += maxCount;
            for (int j = 0; j < ranges.length; j++) {
                if (ranges[j][1] > ranges[maxJ][0] && ranges[j][1] <= ranges[maxJ][1]) {
                    ranges[j][1] = ranges[maxJ][0];
                } else if (ranges[j][1] > ranges[maxJ][1]) {
                    if (ranges[j][0] > ranges[maxJ][0] && ranges[j][0] <= ranges[maxJ][1]) {
                        ranges[j][0] = ranges[maxJ][1];
                    }
                }
            }
            ranges[maxJ][0] = ranges[maxJ][1];
        }
        PrintWriter pw = new PrintWriter(new FileWriter("lifeguards.out"));
        pw.println(maxTime);
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        Lifeguards2 prob = new Lifeguards2();
        prob.readFile();
        prob.solve();
    }

}
