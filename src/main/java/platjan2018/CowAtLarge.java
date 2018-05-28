/*
 * Created by cravuri on 1/21/18
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CowAtLarge {

    int N;
    List<Integer>[] adj;
    int[] parent, height;
    int[] minFarmers;

    int[] euler;

    int lca(int i, int j) {
        return 0;
    }

    int dis(int i, int j) {
        return height[i] + height[j] - 2 * height[lca(i, j)];
    }

    void solve() {
        Set<Integer> leaves = new HashSet<>();
        for (int i = 0; i < adj.length; i++) {
            if (adj[i].size() == 1) {
                leaves.add(i);
            }
        }
        int root = 0;
        while (leaves.contains(root)) {
            root++;
        }

        for (int i = 0; i < N; i++) {

        }
    }

    public static void main(String[] args) {

    }

}
