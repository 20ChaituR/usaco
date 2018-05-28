import java.util.*;

/**
 * Created by usaco15 on 5/26/18.
 */
public class SweetGrass {

    int N, M;
    List<List<Integer>> adj = new ArrayList<>();

    void readFile() {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        for (int i = 0; i < N; i++) {
            List<Integer> a = new ArrayList<>();
            adj.add(a);
//            backwards.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            int a = sc.nextInt() - 1;
            int b = sc.nextInt() - 1;
            adj.get(a).add(b);
//            backwards.get(b).add(a);
        }
    }

//    List<List<Integer>> backwards = new ArrayList<>();

    boolean[] vis;

    boolean canReach(int ban, int u) {
        if (u == N - 1) {
            return true;
        }
        if (vis[u]) {
            return false;
        }
        if (u == ban) {
            return false;
        }
        vis[u] = true;
        for (int v : adj.get(u)) {
            if (v != ban) {
                if (canReach(ban, v)) {
                    return true;
                }
            }
        }
        return false;
    }

    void solve() {
        List<Integer> pts = new ArrayList<>();
        for (int i = 1; i < N - 1; i++) {
            vis = new boolean[N];
            if (!canReach(i, 0)) {
                pts.add(i + 1);
            }
        }
        System.out.println(pts.size());
        for (int i : pts) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        SweetGrass prob = new SweetGrass();
        prob.readFile();
        prob.solve();
    }
}