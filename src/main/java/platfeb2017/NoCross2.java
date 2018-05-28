import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class NoCross2 {

    int N;
    int[] s1, s2;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("nocross.in"));
        N = sc.nextInt();
        s1 = new int[N];
        s2 = new int[N];
        for (int i = 0; i < s1.length; i++) {
            s1[i] = sc.nextInt();
        }
        for (int i = 0; i < s2.length; i++) {
            s2[i] = sc.nextInt();
        }
    }

    void solve() throws IOException {
        int[][] dp = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < i; j++) {
                for (int k = dp[j][1] + 1; k <= N; k++) {
                    if (Math.abs(s1[i - 1] - s2[k - 1]) <= 4) {
                        if (dp[j][0] + 1 >= dp[i][0]) {
                            dp[i][0] = dp[j][0] + 1;
                            dp[i][1] = k;
                        }
                        break;
                    }
                }
            }
        }
        int max = 0;
        for (int i = 0; i <= N; i++) {
            max = Math.max(dp[i][0], max);
        }
        PrintWriter pw = new PrintWriter(new FileWriter("nocross.out"));
        pw.println(max);
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        NoCross2 prob = new NoCross2();
        prob.readFile();
        prob.solve();
    }

}
