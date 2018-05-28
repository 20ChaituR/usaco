/*
 * Created by cravuri on 1/21/18
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Lifeguards {

    int N, K;
    int[][] ranges;

    int MAXT;

    int[] lazy;
    int[] seg;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("lifeguards.in"));
        N = sc.nextInt();
        K = sc.nextInt();
        ranges = new int[N][2];
        MAXT = 0;
        for (int i = 0; i < N; i++) {
            ranges[i] = new int[] {sc.nextInt() + 1, sc.nextInt()};
            MAXT = Math.max(MAXT, ranges[i][1]);
        }
        lazy = new int[4 * MAXT];
        seg = new int[4 * MAXT];
    }

    int count(int[] range) {
        return query(1, 1, MAXT, range[0], range[1]);
    }

    void update(int[] range) {
        update(1, 1, MAXT, range[0], range[1]);
    }

    void build(int node, int l, int r) {
        if (l == r) {
            seg[node] = 1;
            return;
        }
        int mid = (l + r) / 2;
        build(2 * node, l, mid);
        build(2 * node + 1, mid + 1, r);
        seg[node] = seg[2 * node] + seg[2 * node + 1];
    }

    void down(int node, int l, int r) {
        if (lazy[node] == 0) {
            return;
        }
        int mid = (l + r) / 2;
        // left
        seg[2 * node] = 0;
        lazy[2 * node] = 1;
        // right
        seg[2 * node + 1] = 0;
        lazy[2 * node + 1] = 1;
        lazy[node] = 0;
    }

    int query(int node, int l, int r, int a, int b) {
        if (node >= 4 * MAXT) {
            return 0;
        }
        if (a <= l && r <= b) {
            return seg[node];
        }
        down(node, l, r);
        int mid = (l + r) / 2;
        int ans = 0;
        if (mid >= a) {
            ans += query(2 * node, l, mid, a, b);
        }
        if (mid < b) {
            ans += query(2 * node + 1, mid + 1, r, a, b);
        }
        return ans;
    }

    void update(int node, int l, int r, int a, int b) {
        if (node >= 4 * MAXT) {
            return;
        }
        if (a <= l && r <= b) {
            seg[node] = 0;
            lazy[node] = 1;
            return;
        }
        down(node, l, r);
        int mid = (l + r) / 2;
        if (mid >= a) {
            update(2 * node, l, mid, a, b);
        }
        if (mid < b) {
            update(2 * node + 1, mid + 1, r, a, b);
        }
        seg[node] = seg[2 * node] + seg[2 * node + 1];
    }

    void solve() throws IOException {
        build(1, 1, MAXT);
        int maxTime = 0;
        for (int i = 0; i < N - K; i++) {
            int maxCount = 0;
            int maxJ = 0;
            for (int j = 0; j < ranges.length; j++) {
                int x = count(ranges[j]);
                if (x > maxCount) {
                    maxCount = x;
                    maxJ = j;
                }
            }
            maxTime += maxCount;
            update(ranges[maxJ]);
        }
        PrintWriter pw = new PrintWriter(new FileWriter("lifeguards.out"));
        pw.println(maxTime);
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        Lifeguards prob = new Lifeguards();
        prob.readFile();
        prob.solve();
    }

}
