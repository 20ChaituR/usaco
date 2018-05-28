/*
 * Created by cravuri on 5/27/18
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

/**
 * A KD Tree is a tree on k dimensions that can answer queries over kd ranges.
 * this one finds the number of points in a k dimensional space bounded on all sides
 *
 * input format:
 * n (number of points)
 * k (number of dimensions)
 * all points ( each point = [x, y, z, ...] )
 * q (number of queries)
 * all queries ( each query = [[minx, miny, minz, ...], [maxx, maxy, maxz, ...]] )
 */
public class KDTree {

    int n;
    int k;
    int[][] points;

    int q;
    int[][][] queries; // queries[q][dim][0, 1]

    void readFile() {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        k = sc.nextInt();
        points = new int[n][k];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < k; j++) {
                points[i][j] = sc.nextInt();
            }
        }
        q = sc.nextInt();
        queries = new int[q][k][2];
        for (int i = 0; i < q; i++) {
            for (int m = 0; m < 2; m++) {
                for (int j = 0; j < k; j++) {
                    queries[i][j][m] = sc.nextInt();
                }
            }
        }
    }

    int findNum(Node root, int q, int dim) {
        if (root == null) {
            return 0;
        }

        if (root.loc[dim] < queries[q][dim][0]) {
            return findNum(root.right, q, (dim + 1) % k);
        } else if (root.loc[dim] > queries[q][dim][1]) {
            return findNum(root.left, q, (dim + 1) % k);
        } else {
            int ans = 1;
            for (int d = 0; d < k; d++) {
                if (root.loc[d] < queries[q][d][0] || root.loc[d] > queries[q][d][1]) {
                    ans = 0;
                    break;
                }
            }
            return ans + findNum(root.left, q, (dim + 1) % k)
                    + findNum(root.right, q, (dim + 1) % k);
        }
    }

    Node buildTree(int s, int e, final int dim) {
        if (e <= s) {
            return null;
        }
        Arrays.sort(points, s, e, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[dim] - o2[dim];
            }
        });
        int m = (s + e) / 2;
        Node root = new Node(points[m]);
        root.left = buildTree(s, m, (dim + 1) % k);
        root.right = buildTree(m + 1, e, (dim + 1) % k);
        return root;
    }

    void solve() {
        Node root = buildTree(0, n, 0);
        for (int i = 0; i < q; i++) {
            System.out.println(findNum(root, i, 0));
        }
    }

    public static void main(String[] args) {
        KDTree prob = new KDTree();
        prob.readFile();
        prob.solve();
    }

    class Node {
        Node left;
        Node right;
        int[] loc;

        public Node(int[] loc) {
            this.loc = loc;
        }
    }

}
