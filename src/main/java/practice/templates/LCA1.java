import java.util.List;

public class LCA1 {

    int N; // number of nodes
    int r = 0; // root is 0
    int[] p; // parent of i
    int[] pw; // weight of node from i to parent[i]
    int[] L; // level i is on
    List<int[]>[] adj; // adj[a].get(i) 1 is dist from a to 0

    boolean[] vis;

    void dfs(int cur) {
        for (int[] a : adj[cur]) {
            if (!vis[a[0]]) {
                vis[a[0]] = true;
                p[a[0]] = cur;
                pw[a[0]] = a[1];
                L[a[0]] = L[cur] + 1;
                dfs(a[0]);
            }
        }
    }

    void preCompute() {
        vis = new boolean[N];
        vis[r] = true;
        p[r] = r;
        pw[r] = -1;
        L[r] = 0;
        dfs(r);
    }

    int[][] fmemo;
    int f(int u, int i) {
        if (i == 0) {
            return p[u];
        }
        if (fmemo[u][i] != -1) {
            return fmemo[u][i];
        }
        fmemo[u][i] = f(f(u, i - 1), i - 1);
        return fmemo[u][i];
    }

    int[][] gmemo;
    int g(int u, int i) {
        if (i == 0) {
            return pw[u];
        }
        if (gmemo[u][i] != -1) {
            return gmemo[u][i];
        }
        gmemo[u][i] = g(u, i - 1) + g(f(u, i - 1), i - 1);
        return gmemo[u][i];
    }

    int LCA(int a, int b) {
        preCompute();
        int k = (int) Math.ceil(Math.log((double) N));
        fmemo = new int[N][k];
        gmemo = new int[N][k];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < k; j++) {
                fmemo[i][j] = -1;
                gmemo[i][j] = -1;
            }
        }
        int u = a;
        int v = b;
        if (L[v] < L[u]) {
            u = b;
            v = a;
        }
        k = (int) Math.ceil(Math.log((double) L[u]));
        for (int i = k; i >= 0; i--) {
            if (L[f(u, i)] >= L[v]) {
                break;
            } else {
                u = f(u, i);
            }
        }
        if (u == v) {
            return v;
        }
        k = (int) Math.ceil(Math.log((double) L[u]));
        for (int i = k; i >= 0; i--) {
            if (f(u, i) == f(v, i)) {
                return p[v];
            } else {
                u = f(u, i);
                v = f(v, i);
            }
        }
        return r;
    }

}
