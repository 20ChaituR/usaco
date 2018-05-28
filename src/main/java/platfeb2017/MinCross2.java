import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class MinCross2 {

    int N;
    Map<Integer, Integer> order = new HashMap<>();
    int[] s;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("mincross.in"));
        N = sc.nextInt();
        s = new int[N];
        for (int i = 0; i < N; i++) {
            int a = sc.nextInt() - 1;
            order.put(a, i);
        }
        for (int i = 0; i < N; i++) {
            s[i] = sc.nextInt() - 1;
        }
        for (int i = 0; i < s.length; i++) {
            s[i] = order.get(s[i]);
        }
    }

    void solve() throws IOException {
        int[] crossing = new int[N];
        int min = 0;
        for (int i = 0; i < s.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (s[j] > s[i]) {
                    crossing[s[i]]++;
                }
            }
            min += crossing[s[i]];
        }
        for (int i = 1; i < N; i++) {
            int x = s[s.length - i];
            int num = 0;
            for (int j = 0; j < x; j++) {
                crossing[j]++;
            }
            crossing[x] = 0;
            for (int cross : crossing) {
                num += cross;
            }
            min = Math.min(min, num);
        }
        PrintWriter pw = new PrintWriter(new FileWriter("mincross.out"));
        pw.println(min);
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        MinCross2 prob = new MinCross2();
        prob.readFile();
        prob.solve();
    }

}
