import java.io.InputStreamReader;
import java.util.Scanner;

public class LongestCommonSubsequence2 {

    String s1;
    String s2;

    void readFile() {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        s1 = sc.next();
        s2 = sc.next();
    }

    void dp() {
        int[] dpPrev = new int[s1.length() + 1];
        int[] dpCurr = new int[s1.length() + 1];
        for (int i = 0; i < s1.length(); i++) {
            dpPrev[i] = 0;
            dpCurr[i] = 0;
        }
        for (int i = 0; i <= s2.length(); i++) {
            for (int j = 0; j <= s1.length(); j++) {
                if (i == 0 || j == 0) {
                    dpCurr[j] = 0;
                } else if (s1.charAt(j - 1) == s2.charAt(i - 1)) {
                    dpCurr[j] = dpPrev[j - 1] + 1;
                } else {
                    dpCurr[j] = Math.max(dpCurr[j - 1], dpPrev[j]);
                }
            }
            System.arraycopy(dpCurr, 0, dpPrev, 0, s1.length());
        }
        System.out.println(dpCurr[s1.length()]);
    }

    public static void main(String[] args) {
        LongestCommonSubsequence2 prob = new LongestCommonSubsequence2();
        prob.readFile();
        prob.dp();
    }

}
