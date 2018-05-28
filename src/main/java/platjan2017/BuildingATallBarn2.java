import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class BuildingATallBarn2 {

    int N, K;
    double[] work;
    final double INF = (int) 1e13;

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
        double[][] dp = new double[2][K + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                double min = INF;
                for (int k = 0; k < j; k++) {
                    min = Math.min(dp[0][k] + work[i] / (j - k), min);
                }
                dp[1][j] = min;
            }
            for (int j = 0; j < dp[0].length; j++) {
                dp[0][j] = dp[1][j];
                dp[1][j] = 0;
            }
        }
        PrintWriter pw = new PrintWriter(new FileWriter("tallbarn.out"));
        pw.println(Math.round(dp[0][K]));
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        BuildingATallBarn2 prob = new BuildingATallBarn2();
        prob.readFile();
        prob.solve();
    }

}
