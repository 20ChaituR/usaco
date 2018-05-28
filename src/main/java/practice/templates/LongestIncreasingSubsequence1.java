import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class LongestIncreasingSubsequence1 {

    static int[] a;
    static int[] f;

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(text.readLine());
        a = new int[n];
        f = new int[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(text.readLine());
            a[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static int longestSubsequence() {
        for (int i = 0; i < a.length; i++) {
            f[i] = 1;
            for (int j = 0; j < i; j++) {
                if (a[j] < a[i]) {
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

    public static void main(String[] args) throws IOException {
        readFile();
        System.out.println(longestSubsequence());
    }

}
