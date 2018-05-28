import java.io.InputStreamReader;
import java.util.Scanner;

public class SegmentTree {

    final int N = (int) 1e5 + 5;
    int n;
    int[] arr = new int[N], lazy = new int[4 * N];
    long[] seg = new long[4 * N];

    void readFile() {
        Scanner sc = new Scanner(new InputStreamReader(System.in));
        n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            arr[i] = sc.nextInt();
        }
    }

    // build tree
    void build(int node, int l, int r) {
        if (l == r) {
            seg[node] = arr[l];
            return;
        }
        int mid = (l + r) / 2;
        build(2 * node, l, mid);
        build(2 * node + 1, mid + 1, r);
        seg[node] = seg[2 * node] + seg[2 * node + 1];
    }

    // propagate down from node
    void down(int node, int l, int r) {
        if (lazy[node] == 0) {
            return;
        }
        int mid = (l + r) / 2;
        // left
        seg[2 * node] += lazy[node] * (mid - l + 1);
        lazy[2 * node] += lazy[node];
        // right
        seg[2 * node + 1] += lazy[node] * (r - mid);
        lazy[2 * node + 1] += lazy[node];
        lazy[node] = 0;
    }

    // query [a...b]
    long query(int node, int l, int r, int a, int b) {
        if (node >= 4 * n) {
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

    // update [a...b] with x
    void update(int node, int l, int r, int a, int b, int x) {
        if (node >= 4 * n) {
            return;
        }
        if (a <= l && r <= b) {
            seg[node] += x * (r - l + 1);
            lazy[node] += x;
            return;
        }
        down(node, l, r);
        int mid = (l + r) / 2;
        if (mid >= a) {
            update(2 * node, l, mid, a, b, x);
        }
        if (mid < b) {
            update(2 * node + 1, mid + 1, r, a, b, x);
        }
        seg[node] = seg[2 * node] + seg[2 * node + 1];
    }

    void solve() {
        readFile();
        build(1, 1, n);
        // you can now query any range by calling query(1, 1, n, a, b)
        // and you can update any range by calling update(1, 1, n, a, b, x)
        System.out.println(query(1, 1, n, 2, 5)); // 11
        update(1, 1, n, 1, 6, 5);
        System.out.println(query(1, 1, n, 2, 5)); // 31
    }

    public static void main(String[] args) {
        new SegmentTree().solve();
    }

}
