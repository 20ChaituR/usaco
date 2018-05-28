import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class TeamBuilding {

    int N, M, K;
    long[] fj;
    long[] fp;
    final long mod = 1000000009;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("team.in"));
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        fj = new long[N];
        fp = new long[M];
        for (int i = 0; i < N; i++) {
            fj[i] = sc.nextLong();
        }
        for (int j = 0; j < M; j++) {
            fp[j] = sc.nextLong();
        }
    }

    void solve2() throws IOException {
        Arrays.sort(fj);
        Arrays.sort(fp);
        long[][] sumOld = new long[N + 1][M + 1];
        long[][] sumNew = new long[N + 1][M + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                sumOld[i][j] = 1;
            }
        }
        for (int k = 1; k <= K; k++) {
            for (int i = 0; i <= N; i++) {
                for (int j = 0; j <= M; j++) {
                    if (i > 0) {
                        sumNew[i][j] += sumNew[i - 1][j];
                        sumNew[i][j] %= mod;
                    }
                    if (j > 0) {
                        sumNew[i][j] += sumNew[i][j - 1];
                        sumNew[i][j] %= mod;
                    }
                    if (i > 0 && j > 0) {
                        sumNew[i][j] -= sumNew[i - 1][j - 1];
                        sumNew[i][j] %= mod;
                    }
                    if (i > 0 && j > 0 && fj[i - 1] > fp[j - 1]) {
                        sumNew[i][j] += sumOld[i - 1][j - 1];
                        sumNew[i][j] %= mod;
                    }
                    sumNew[i][j] %= mod;
                }
            }
            for (int i = 0; i <= N; i++) {
                for (int j = 0; j <= M; j++) {
                    sumOld[i][j] = sumNew[i][j];
                    sumNew[i][j] = 0;
                }
            }
        }
        PrintWriter pw = new PrintWriter(new FileWriter("team.out"));
        pw.println(sumOld[N][M]);
        pw.close();
    }

//    void solve() throws IOException {
//        Arrays.sort(fj);
//        Arrays.sort(fp);
//        long[][][] sum = new long[K + 1][N + 1][M + 1];
//        for (int i = 0; i <= N; i++) {
//            for (int j = 0; j <= M; j++) {
//                sum[0][i][j] = 1;
//            }
//        }
//        for (int k = 1; k <= K; k++) {
//            for (int i = 0; i <= N; i++) {
//                for (int j = 0; j <= M; j++) {
//                    if (i > 0) {
//                        sum[k][i][j] += sum[k][i - 1][j];
//                        sum[k][i][j] %= mod;
//                    }
//                    if (j > 0) {
//                        sum[k][i][j] += sum[k][i][j - 1];
//                        sum[k][i][j] %= mod;
//                    }
//                    if (i > 0 && j > 0) {
//                        sum[k][i][j] -= sum[k][i - 1][j - 1];
//                        sum[k][i][j] %= mod;
//                    }
//                    if (i > 0 && j > 0 && fj[i - 1] > fp[j - 1]) {
//                        sum[k][i][j] += sum[k - 1][i - 1][j - 1];
//                        sum[k][i][j] %= mod;
//                    }
//                    sum[k][i][j] %= mod;
//                }
//            }
//        }
//        PrintWriter pw = new PrintWriter(new FileWriter("team.out"));
//        pw.println(sum[K][N][M]);
//        pw.close();
//    }

    public static void main(String[] args) throws IOException {
        TeamBuilding prob = new TeamBuilding();
        prob.readFile();
        prob.solve2();
    }

}
