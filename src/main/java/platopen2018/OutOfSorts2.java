/*
 * Created by cravuri on 3/25/18
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

public class OutOfSorts2 {

    int N;
    int[] A;
    int[] ng;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("sort.in"));
        N = sc.nextInt();
        A = new int[N + 1];
        for (int i = 0; i < N; i++) {
            A[i] = sc.nextInt();
        }
        A[N] = (int) 1e9 + 3;
    }

    void nextGreatest() {
        ng = new int[N];
        ng[N - 1] = N;
        Stack<Integer> st = new Stack<>();
        st.add(N - 1);
        for (int i = N - 2; i >= 0; i--) {
            while (!st.isEmpty()) {
                if (A[i] < A[st.peek()]) {
                    ng[i] = st.peek();
                    st.push(i);
                    break;
                } else {
                    st.pop();
                }
            }
            if (st.isEmpty()) {
                ng[i] = N;
                st.push(i);
            }
        }
    }

    int wc = 0;

    void quickSort(int[] A, int l, int r) {
        if (r - l == 1) {
            return;
        }
        wc += r - l;
        int curMax = 0;
        Set<Integer> part = new HashSet<>();
        part.add(l);
        part.add(l + 1);
        while (ng[l] <= r) {
            part.add(ng[l]);
            l = ng[l];
        }
        for (int i = l; i < r; i++) {

        }
    }

    int calc(int l, int r) {
        if (r - l == 1) {
            return 0;
        }
        int ans = r - l;
        l++;
        while (l < r) {
            ans += calc(l, ng[l]);
            l = ng[l];
        }
        return ans;
    }

    void solve() throws IOException {
        nextGreatest();
        PrintWriter pw = new PrintWriter(new FileWriter("sort.out"));
        pw.println(calc(0, N));
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        OutOfSorts2 prob = new OutOfSorts2();
        prob.readFile();
        prob.solve();
    }

}
