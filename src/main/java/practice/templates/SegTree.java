/*
 * Created by cravuri on 5/27/18
 */

import java.util.Scanner;

public class SegTree {

    int N;
    int[] arr;
    int[] lazy; // can add in a range
    int[] seg;

    void readFile() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        arr = new int[N + 1];
        for (int i = 1; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }

        lazy = new int[4 * N + 1];
        seg = new int[4 * N + 1];
    }

    void buildTree(int node, int l, int r) {
        if (node >= arr.length) {
            return;
        }
        if (l == r) {
            seg[node] = arr[l];
            return;
        }
        int mid = (l + r) / 2;
        buildTree(2 * node, l, mid);
        buildTree(2 * node + 1, mid + 1, r);
        seg[node] = Math.min(seg[2 * node], seg[2 * node + 1]);
    }

    // finds min between a and b
    int query(int node, int l, int r, int a, int b) {
        if (node >= arr.length) {
            return 0;
        }
        if (lazy[node] != 0) {
            seg[node] += lazy[node];
            lazy[2 * node] += lazy[node];
            lazy[2 * node + 1] += lazy[node];
            lazy[node] = 0;
        }
        if (a <= l && r <= b) {
            return seg[node];
        }
        if (r < a || l > b) {
            return 0;
        }
        int m = (l + r) / 2;
        return Math.min(query(2 * node, l, m, a, b), query(2 * node + 1, m + 1, r, a, b));
    }

    // adds x to all values between a and b
    void update(int node, int l, int r, int a, int b, int x) {
        if (node >= arr.length) {
            return;
        }
        if (lazy[node] != 0) {
            seg[node] += lazy[node];
            lazy[2 * node] += lazy[node];
            lazy[2 * node + 1] += lazy[node];
            lazy[node] = 0;
        }
        if (a <= l && r <= b) {
            lazy[node] += x;
            return;
        }
        if (r < a || l > b) {
            return;
        }
        int m = (l + r) / 2;
        update(2 * node, l, m, a, b, x);
        update(2 * node + 1, m + 1, r, a, b, x);
    }

    void solve() {
        buildTree(1, 1, N);
        int a = 2;
        int b = 5;
        query(1, 1, N, a, b);
        int x = 3;
        update(1, 1, N, a, b, x);
    }

    public static void main(String[] args) {
        SegTree prob = new SegTree();
        prob.readFile();
        prob.solve();
    }

}
