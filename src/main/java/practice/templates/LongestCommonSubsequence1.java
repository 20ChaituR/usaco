import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LongestCommonSubsequence1 {

    static int[][] memo;
    static String s1;
    static String s2;

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new InputStreamReader(System.in));
        s1 = text.readLine();
        s2 = text.readLine();
        memo = new int[s1.length()][s2.length()];
        for (int i = 0; i < memo.length; i++) {
            for (int j = 0; j < memo[0].length; j++) {
                memo[i][j] = -1;
            }
        }
    }

    public static int lcs(int i, int j) {
        if (i == -1 || j == -1) {
            return 0;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            memo[i][j] = lcs(i - 1, j - 1) + 1;
            return memo[i][j];
        }
        memo[i][j] = Math.max(lcs(i - 1, j), lcs(i, j - 1));
        return memo[i][j];
    }

    public static void main(String[] args) throws IOException {
        readFile();
        System.out.println(lcs(s1.length() - 1, s2.length() - 1));
    }

}
