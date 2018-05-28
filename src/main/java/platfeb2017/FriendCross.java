import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FriendCross {

    int N, K;
    Map<Integer, Integer> order = new HashMap<>();
    int[] s1;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("friendcross.in"));
        N = sc.nextInt();
        K = sc.nextInt();
        s1 = new int[N];
        for (int i = 0; i < N; i++) {
            int a = sc.nextInt() - 1;
            order.put(a, i);
        }
        for (int i = 0; i < N; i++) {
            s1[i] = sc.nextInt() - 1;
        }
    }

    void solve() throws IOException {
        int numCross = 0;
        for (int i = 0; i < s1.length; i++) {
            int j = order.get(s1[i]);
            for (int k = 0; k < s1.length; k++) {
                if (s1[i] - s1[k] <= K) {
                    continue;
                }
                int l = order.get(s1[k]);
                if (((i < k) && (l < j)) || ((k < i) && (j < l))) {
                    numCross++;
                }
            }
        }
        PrintWriter pw = new PrintWriter(new FileWriter("friendcross.out"));
        pw.println(numCross);
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        FriendCross prob = new FriendCross();
        prob.readFile();
        prob.solve();
    }

}
