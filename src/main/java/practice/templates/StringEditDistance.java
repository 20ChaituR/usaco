import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringEditDistance {

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

    public static int d(int i, int j) {
        if (j == -1) {
            return i + 1;
        }
        if (i == -1) {
            return j + 1;
        }
        if (memo[i][j] != -1) {
            return memo[i][j];
        }
        if (s1.charAt(i) == s2.charAt(j)) {
            memo[i][j] = d(i - 1, j - 1);
            return memo[i][j];
        }
        memo[i][j] = Math.min(d(i - 1, j) + 1, Math.min(d(i, j - 1) + 1, d(i - 1, j - 1) + 1));
        return memo[i][j];
    }

    public static void main(String[] args) throws IOException {
        readFile();
        System.out.println(d(s1.length() - 1, s2.length() - 1));
    }

}
