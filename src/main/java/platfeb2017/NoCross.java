import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class NoCross {

    int N;
    int[] s1, s2;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("nocross.in"));
        N = sc.nextInt();
        s1 = new int[N];
        s2 = new int[N];
        for (int i = 0; i < s1.length; i++) {
            s1[i] = sc.nextInt();
        }
        for (int i = 0; i < s2.length; i++) {
            s2[i] = sc.nextInt();
        }
    }

    void solve() throws IOException {
        // max crosswalks using first i cows in side one going up to j
        // i, j = (i - 1, j) or if (i --> j is possible) then 1 + max(i - 1, j - 1...0)
        int[] oldDP = new int[N + 1];
        int[] newDP = new int[N + 1];
        int[] oldMax = new int[N + 1];
        int[] newMax = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            System.arraycopy(newMax, 0, oldMax, 0, N + 1);
            System.arraycopy(newDP, 0, oldDP, 0, N + 1);
            newMax = new int[N + 1];
            newDP = new int[N + 1];
            for (int j = 1; j <= N; j++) {
                newDP[j] = oldDP[j];
                if (Math.abs(s1[i - 1] - s2[j - 1]) <= 4) {
                    newDP[j] = Math.max(newDP[j], 1 + oldMax[j - 1]);
                }
                newMax[j] = Math.max(newMax[j - 1], newDP[j]);
            }
        }
        int crosswalks = 0;
        for (int i = 0; i < oldMax.length; i++) {
            crosswalks = Math.max(crosswalks, oldMax[i]);
        }
        PrintWriter pw = new PrintWriter(new FileWriter("nocross.out"));
        pw.println(crosswalks);
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        NoCross prob = new NoCross();
        prob.readFile();
        prob.solve();
    }

}
