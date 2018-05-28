import java.io.InputStreamReader;
import java.util.Scanner;

public class LongestIncreasingSubsequence2 {

    int N;
    int[] x;

    void readFile() {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        N = sc.nextInt();
        x = new int[N];
        for (int i = 0; i < x.length; i++) {
            x[i] = sc.nextInt();
        }
    }

    void solve() {
        int[] m = new int[N + 1];
        int L = 0;
        for (int i = 0; i < N; i++) {
            int lo = 1;
            int hi = L;
            while (lo <= hi) {
                int mid = (lo + hi + 1) / 2;
                if (x[m[mid]] < x[i]) {
                    lo = mid + 1;
                } else {
                    hi  = mid - 1;
                }
            }
            int newL = lo;
            m[newL] = i;

            if (newL > L) {
                L = newL;
            }
        }
        System.out.println(x.length - L);
    }

    public static void main(String[] args) {
        LongestIncreasingSubsequence2 prob = new LongestIncreasingSubsequence2();
        prob.readFile();
        prob.solve();
    }

}
