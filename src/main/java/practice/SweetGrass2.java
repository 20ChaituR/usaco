/*
 * Created by cravuri on 5/26/18
 *
 * implementation of spencer's solution
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class SweetGrass2 {

    int N, M;
    List<List<Integer>> adj = new ArrayList<>();

    void readFile() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<Integer>());
        }
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            adj.get(a).add(b);
        }
    }

    HashMap<Integer, Integer> path;
    HashMap<Integer, Integer> revPath;

    int[] next;
    boolean[] vis;

    int findDeepest(int u) {
        Queue<Integer> q = new ArrayDeque<>();
        int min = N;
        q.add(u);
        vis[u] = true;

        while (!q.isEmpty()) {
            int x = q.remove();
            for (int y : adj.get(x)) {
                if (!vis[y]) {
                    if (path.keySet().contains(y)) {
                        min = Math.min(min, path.get(y));
                        vis[y] = true;
                    } else {
                        q.add(y);
                        vis[y] = true;
                    }
                }
            }
        }

        return min;
    }

    int findNext(int u) {
        int min = N;
        for (int v : adj.get(revPath.get(u))) {
            if (!path.keySet().contains(v)) {
                int deep = findDeepest(v);
                min = Math.min(min, deep);
            } else {
                min = Math.min(min, path.get(v));
            }
        }
        return min;
    }

    void solve() {
        vis = new boolean[N];
        int[] prev = new int[N];
        for (int i = 0; i < N; i++) {
            prev[i] = -1;
        }
        Queue<Integer> q = new ArrayDeque<>();
        q.add(0);
        vis[0] = true;
        prev[0] = -1;
        while (!q.isEmpty()) {
            int u = q.remove();
            for (int v : adj.get(u)) {
                if (!vis[v]) {
                    q.add(v);
                    vis[v] = true;
                    prev[v] = u;
                    if (v == N - 1) {
                        break;
                    }
                }
            }
        }

        path = new HashMap<>();
        revPath = new HashMap<>();
        int cur = N - 1;
        int count = 0;
        while (cur != -1) {
            path.put(cur, count);
            revPath.put(count, cur);
            count++;
            cur = prev[cur];
        }

        next = new int[path.size()];
        vis = new boolean[N];
        List<Integer> pts = new ArrayList<>();
        int[] minReached = new int[path.size()];
        for (int j = path.size() - 1; j >= 0; j--) {
            if (j == path.size() - 1) {
                minReached[j] = findNext(j);
            } else {
                minReached[j] = minReached[j + 1];
                if (minReached[j] == 0) {
                    continue;
                }
                minReached[j] = Math.min(minReached[j], findNext(j));
            }
        }

        int curMin = N + 1;
        for (int j = path.size() - 1; j > 0; j--) {
            if (j == curMin) {
                pts.add(revPath.get(j) + 1);
            }
            curMin = Math.min(curMin, minReached[j]);
        }

        System.out.println(pts.size());
        Collections.sort(pts);
        for (int i : pts) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        SweetGrass2 prob = new SweetGrass2();
        prob.readFile();
        prob.solve();
    }

}
