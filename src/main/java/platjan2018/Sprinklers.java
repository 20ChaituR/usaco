/*
 * Created by cravuri on 1/21/18
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Sprinklers {

    int N;
    int[][] sprinklers;

    final int MOD = (int) (1e9) + 7;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("sprinklers.in"));
        N = sc.nextInt();
        sprinklers = new int[N][2];
        for (int i = 0; i < sprinklers.length; i++) {
            sprinklers[i] = new int[]{sc.nextInt(), sc.nextInt()};
        }
    }

    // x choose 2 * y choose 2
    int count(int x, int y) {
        int ans = (x * (x + 1) / 2) % MOD;
        ans *= (y * (y + 1) / 2) % MOD;
        ans %= MOD;
        return ans;
    }

    void solve() throws IOException {
        Arrays.sort(sprinklers, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
            }
        });
        int total = 0;
        int minY = N;
        for (int i = 0; i < N; i++) {
            if (sprinklers[i][1] > minY) {
                continue;
            }
            int maxY = sprinklers[i][1];
            for (int j = N - 1; j > i; j--) {
                if (sprinklers[j][1] > maxY) {
                    if (sprinklers[j][1] < minY) {
                        total += count(sprinklers[j][0] - sprinklers[i][0], sprinklers[j][1] - maxY);
                        total %= MOD;
                        maxY = sprinklers[j][1];
                    } else {
                        total += count(sprinklers[j][0] - sprinklers[i][0], sprinklers[j][1] - maxY) - count(sprinklers[j][0] - sprinklers[i][0], sprinklers[j][1] - minY);
                        total %= MOD;
                        break;
                    }
                }
            }
            minY = sprinklers[i][1];
        }
        PrintWriter pw = new PrintWriter(new FileWriter("sprinklers.out"));
        pw.println(total);
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        Sprinklers prob = new Sprinklers();
        prob.readFile();
        prob.solve();
    }

}
