import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SubsequenceReversal {

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

    void reverse(long i) { // reverse subsequence i in vals
        int l = 0;
        int r = vals.length - 1;
        boolean foundl = false;
        boolean foundr = false;
        while (l <= r) {
            if ((i & (1L << (long) (vals.length - l - 1))) != 0) {
                foundl = true;
            }
            if ((i & (1L << (long) (vals.length - r - 1))) != 0) {
                foundr = true;
            }
            if (!foundl) {
                l++;
            }
            if (!foundr) {
                r--;
            }
            if (foundl && foundr) {
                int temp = vals[l];
                vals[l] = vals[r];
                vals[r] = temp;
                l++;
                r--;
                foundl = false;
                foundr = false;
            }
        }
    }

    int longestSubsequence() {
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
        return x;
    }

    void solve() throws IOException {
        int max = 0;
        for (long i = 0; i < 1L << (long) N; i++) {
            if (Long.bitCount(i) % 2 != 0) {
                continue;
            }
            reverse(i);
            max = Math.max(longestSubsequence(), max);
            reverse(i);
        }
        PrintWriter pw = new PrintWriter(new FileWriter("subrev.out"));
        pw.println(max);
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        SubsequenceReversal prob = new SubsequenceReversal();
        prob.readFile();
        prob.solve();
    }

}
