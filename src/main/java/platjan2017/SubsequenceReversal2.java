import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SubsequenceReversal2 {

    int N;
    int[] vals;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("subrev.in"));
        N = sc.nextInt();
        vals = new int[N];
        for (int i = 0; i < N; i++) {
            vals[i] = sc.nextInt();
        }
    }

    long longestSubsequence(int l, int r) {
        int temp = vals[l];
        vals[l] = vals[r];
        vals[r] = temp;
        long[] f = new long[N];
        int[] v = new int[N];
        for (int i = 0; i <= l; i++) {
            f[i] = 1L << (long) (vals.length - i - 1);
            v[i] = 1;
            for (int j = 0; j < i; j++) {
                if (vals[j] <= vals[i]) {
                    if (v[i] < v[j] + 1) {
                        f[i] = f[j] | 1L << (long) (vals.length - i - 1);
                        v[i] = v[j] + 1;
                    }
                }
            }
        }
        for (int i = r; i < vals.length; i++) {
            f[i] = 1L << (long) (vals.length - i - 1);
            v[i] = 1;
            for (int j = 0; j <= l; j++) {
                if (vals[j] <= vals[i]) {
                    if (v[i] < v[j] + 1) {
                        f[i] = f[j] | 1L << (long) (vals.length - i - 1);
                        v[i] = v[j] + 1;
                    }
                }
            }
            for (int j = r; j < i; j++) {
                if (vals[j] <= vals[i]) {
                    if (v[i] < v[j] + 1) {
                        f[i] = f[j] | 1L << (long) (vals.length - i - 1);
                        v[i] = v[j] + 1;
                    }
                }
            }
        }
        long x = 0;
        for (int i = 0; i < f.length; i++) {
            if (Long.bitCount(x) < v[i]) {
                x = f[i];
            }
        }
        temp = vals[l];
        vals[l] = vals[r];
        vals[r] = temp;
        return x;
    }

    void solve() throws IOException {
        int cL = 0;
        int cR = N - 1;
        long subsequence = 0;
        for (int i = 0; i < N; i++) {
            int nL = -1;
            int nR = -1;
            long max = 0;
            for (int l = cL; l <= cR; l++) {
                for (int r = cR; r >= cL; r--) {
                    if (l >= r) {
                        continue;
                    }
                    long x = longestSubsequence(l, r);
                    if (Long.bitCount(x) > Long.bitCount(max)) {
                        max = x;
                        nL = l;
                        nR = r;
                    }
                }
            }
            subsequence |= max;
            if (nL == nR) {
                break;
            }
            cL = nL + 1;
            cR = nR - 1;
        }
        PrintWriter pw = new PrintWriter(new FileWriter("subrev.out"));
        pw.println(Long.bitCount(subsequence));
        pw.close();
    }

    int longestSubsequence2(int l, int r) {
        int temp = vals[l];
        vals[l] = vals[r];
        vals[r] = temp;
        int[] f = new int[N];
        for (int i = 0; i < vals.length; i++) {
            f[i] = 1;
            for (int j = 0; j < i; j++) {
                if (vals[j] <= vals[i]) {
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
        }
        int x = -1;
        for (int i = 0; i < f.length; i++) {
            x = Math.max(x, f[i]);
        }
        temp = vals[l];
        vals[l] = vals[r];
        vals[r] = temp;
        return x;
    }

    void solve2() throws IOException {
        int cL = 0;
        int cR = N - 1;
        int lis = 0;
        for (int i = 0; i < N; i++) {
            int nL = -1;
            int nR = -1;
            int max = 0;
            for (int l = cL; l <= cR; l++) {
                for (int r = cR; r >= cL; r--) {
                    if (l >= r) {
                        continue;
                    }
                    int x = longestSubsequence2(l, r);
                    if (x > max) {
                        max = x;
                        nL = l;
                        nR = r;
                    }
                }
            }
            lis = Math.max(lis, max);
            if (nL == nR) {
                break;
            }
            int temp = vals[nL];
            vals[nL] = vals[nR];
            vals[nR] = temp;
            cL = nL + 1;
            cR = nR - 1;
        }
        PrintWriter pw = new PrintWriter(new FileWriter("subrev.out"));
        pw.println(lis);
        pw.close();
    }


    public static void main(String[] args) throws IOException {
        SubsequenceReversal2 prob = new SubsequenceReversal2();
        prob.readFile();
        prob.solve2();
    }

}
