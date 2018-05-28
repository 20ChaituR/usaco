import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class BuildingATallBarn {

    int N, K;
    double[] work;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("tallbarn.in"));
        N = sc.nextInt();
        K = sc.nextInt();
        work = new double[N];
        for (int i = 0; i < work.length; i++) {
            work[i] = sc.nextInt();
        }
    }

    void solve() throws IOException {
        double[][] dp = new double[N + 1][K + 1];
        double[][] min = new double[K + 1][2];
        int[][] ind = new int[K + 1][2];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (j == 0) {
                    dp[i][j] = Integer.MAX_VALUE;
                    min[j][1] = dp[i][j];
                    ind[j][1] = 0;
                } else {
                    dp[i][j] = min[j - 1][0] + work[i - 1] / (j - ind[j - 1][0]);
                    if (dp[i][j] < min[j - 1][1]) {
                        ind[j][1] = j;
                        min[j][1] = dp[i][j];
                    } else {
                        min[j][1] = min[j - 1][1];
                        ind[j][1] = ind[j - 1][1];
                    }
                }
            }
            for (int j = 0; j < min.length; j++) {
                min[j][0] = min[j][1];
                min[j][1] = 0;
                ind[j][0] = ind[j][1];
                ind[j][1] = 0;
            }
        }
        PrintWriter pw = new PrintWriter(new FileWriter("tallbarn.out"));
        pw.println(dp[N][K]);
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        BuildingATallBarn prob = new BuildingATallBarn();
        prob.readFile();
        prob.solve();
    }

}
