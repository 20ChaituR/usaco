/*
 * Created by cravuri on 5/27/18
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HeavyLightDecomposition {

    // given
    int N;
    List<List<Integer>> children = new ArrayList<>();
    int[] sizes;
    int[] parent;
    int[] level;

    void readFile() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        
    }

    void buildDecomp() {
        dfs(0);
        lcaPrecomp();
        getPaths(0);
    }

    void dfs(int u) {
        if (u == 0) {
            level[u] = 0;
        }
        for (int v : children.get(u)) {
            parent[v] = u;
            level[v] = 1 + level[u];
            dfs(v);
            sizes[u] += sizes[v];
        }
    }

    // what we want to make
    List<List<Integer>> paths = new ArrayList<>();
    List<SegmentTree> seg = new ArrayList<>();
    int[] pathOfNode;
    int[] pos;

    void getPaths(int u) {
        List<Integer> heavyPath = new ArrayList<>();
        int cur = u;
        heavyPath.add(cur);
        int maxSize = 0;
        while (!children.get(cur).isEmpty()) {
            int maxInd = children.get(cur).get(0);
            for (int v : children.get(cur)) {
                if (sizes[v] > sizes[maxInd]) {
                    maxInd = v;
                }
                if (cur == u && sizes[v] > maxSize) {
                    maxSize = sizes[v];
                }
            }
            cur = maxInd;
            heavyPath.add(cur);
            pos[cur] = heavyPath.size();
            pathOfNode[cur] = paths.size();
        }

        paths.add(heavyPath);
        seg.add(new SegmentTree(heavyPath));

        for (int v : children.get(u)) {
            if (sizes[v] != maxSize) {
                getPaths(v);
            }
        }
    }

    void update(int u, int v, int x) {
        int l = lca(u, v);
        int cur = u;
        while (pathOfNode[cur] != pathOfNode[l]) {
            seg.get(pathOfNode[cur]).update(0, pos[cur], x);
            cur = parent[paths.get(pathOfNode[cur]).get(0)];
        }
        seg.get(pathOfNode[cur]).update(l, pos[cur], x);

        cur = v;
        while (pathOfNode[cur] != pathOfNode[l]) {
            seg.get(pathOfNode[cur]).update(0, pos[cur], x);
            cur = parent[paths.get(pathOfNode[cur]).get(0)];
        }
        seg.get(pathOfNode[cur]).update(l, pos[cur], x);
    }

    int query(int u, int v) {
        int l = lca(u, v);
        int min = Integer.MAX_VALUE;
        int cur = u;
        while (pathOfNode[cur] != pathOfNode[l]) {
            min = Math.min(min, seg.get(pathOfNode[cur]).query(0, pos[cur]));
            cur = parent[paths.get(pathOfNode[cur]).get(0)];
        }
        min = Math.min(min, seg.get(pathOfNode[cur]).query(l, pos[cur]));

        cur = v;
        while (pathOfNode[cur] != pathOfNode[l]) {
            min = Math.min(min, seg.get(pathOfNode[cur]).query(0, pos[cur]));
            cur = parent[paths.get(pathOfNode[cur]).get(0)];
        }
        min = Math.min(min, seg.get(pathOfNode[cur]).query(l, pos[cur]));

        return min;
    }

    int[][] p;

    void lcaPrecomp() {
        int logN;
        for (logN = 1; 1 << logN <= N; logN++) ;
        p = new int[N][logN + 1];
        for (int i = 0; i < N; i++) {
            for (int j = 0; 1 << j < N; j++) {
                p[i][j] = -1;
            }
        }

        for (int j = 0; 1 << j < N; j++) {
            for (int i = 0; i < N; i++) {
                if (j == 0) {
                    p[i][j] = parent[i];
                } else {
                    if (p[i][j - 1] != -1) {
                        p[i][j] = p[p[i][j - 1]][j - 1];
                    }
                }
            }
        }
    }

    int lca(int u, int v) {
        if (level[u] < level[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int log;
        for (log = 1; 1 << log <= level[u]; log++) ;
        log--;

        for (int i = log; i >= 0; i--)
            if (level[u] - (1 << i) >= level[v])
                u = p[u][i];

        if (u == v) {
            return u;
        }

        for (int i = log; i >= 0; i--) {
            if (p[u][i] != -1 && p[u][i] != p[v][i]) {
                u = p[u][1];
                v = p[v][i];
            }
        }

        return parent[u];
    }

    void solve() {
        buildDecomp();

    }

    public static void main(String[] args) {

    }

    class SegmentTree {
        int N;
        int[] arr;
        int[] lazy;
        int[] seg;

        public SegmentTree(List<Integer> list) {
            N = list.size();
            arr = new int[N];
            int i = 0;
            for (int u : list) {
                arr[i] = u;
                i++;
            }

            lazy = new int[4 * N + 1];
            seg = new int[4 * N + 1];

            buildTree(1, 1, N);
        }

        private void buildTree(int node, int l, int r) {
            if (node >= seg.length) {
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
        private int query(int node, int l, int r, int a, int b) {
            if (node >= seg.length) {
                return Integer.MAX_VALUE;
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
                return Integer.MAX_VALUE;
            }
            int m = (l + r) / 2;
            return Math.min(query(2 * node, l, m, a, b), query(2 * node + 1, m + 1, r, a, b));
        }

        public int query(int a, int b) {
            return query(1, 1, arr.length, a, b);
        }

        // adds x to all values between a and b
        private void update(int node, int l, int r, int a, int b, int x) {
            if (node >= seg.length) {
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

        public void update(int a, int b, int x) {
            update(1, 1, arr.length, a, b, x);
        }
    }

}
