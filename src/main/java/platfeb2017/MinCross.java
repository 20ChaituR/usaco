import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MinCross {

    Map<Integer, Integer> order = new HashMap<>();
    int[] s1;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("mincross.in"));
        int N = sc.nextInt();
        s1 = new int[N];
        for (int i = 0; i < N; i++) {
            int a = sc.nextInt() - 1;
            order.put(a, i);
        }
        for (int i = 0; i < N; i++) {
            s1[i] = sc.nextInt() - 1;
        }
    }

//    int countCross() {
//        int numCross = 0;
//        for (int i = 0; i < s1.length; i++) {
//            numCross += Math.abs(s1[i] - i);
//        }
//        return numCross / 2;
//    }

    int binarySearch(List<Integer> a, int e) {
        int l = 0;
        int r = a.size() - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            int x = a.get(m);
            if (x < e) {
                l = m + 1;
            }
            if (x > e) {
                r = m - 1;
            }
        }
        return l;
    }

    int countCross2() {
        int numCross = 0;
        List<Integer> a = new ArrayList<>();
        for (int i = 0; i < s1.length; i++) {
            if (a.isEmpty() || s1[i] > a.get(a.size() - 1)) {
                a.add(s1[i]);
            } else {
                int j = binarySearch(a, s1[i]);
                numCross += a.size() - j;
                a.add(j, s1[i]);
            }
        }
        return numCross;
    }

    void rotate() {
        int x = s1[0];
        for (int i = 0; i < s1.length - 1; i++) {
            s1[i] = s1[i + 1];
        }
        s1[s1.length - 1] = x;
    }

    void solve() throws IOException {
        for (int i = 0; i < s1.length; i++) {
            s1[i] = order.get(s1[i]);
        }
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < s1.length; i++) {
            int x = countCross2();
            min = Math.min(min, x);
            rotate();
        }
        PrintWriter pw = new PrintWriter(new FileWriter("mincross.out"));
        pw.println(min);
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        MinCross prob = new MinCross();
        prob.readFile();
        prob.solve();
    }

}
