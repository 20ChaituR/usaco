/*
 * Created by cravuri on 2/25/18
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class NewBarns {

    List<Integer> parent;
    List<TreeSet<Integer>> children;
    List<Integer> height;

    int Q;
    int[][] query;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("newbarn.in"));
        Q = sc.nextInt();
        query = new int[Q][2];
        for (int i = 0; i < Q; i++) {
            String type = sc.next();
            if (type.equals("B")) {
                query[i] = new int[] {-1, sc.nextInt() - 1};
            } else {
                query[i] = new int[] {1, sc.nextInt() - 1};
            }
        }
        parent = new ArrayList<>();
        children = new ArrayList<>();
        height = new ArrayList<>();
    }

    void solve() throws FileNotFoundException {
        PrintWriter pw = new PrintWriter("newbarn.out");
        for (int[] q : query) {
            if (q[0] == -1) {
                if (q[1] == -2) {
                    parent.add(-1);
                    children.add(new TreeSet<Integer>());
                    height.add(0);
                } else {
                    parent.add(q[1]);
                    children.add(new TreeSet<Integer>());
                    height.add(0);
                    int p = q[1];
                    int h = 1;
                    while (p != -1) {
                        height.set(p, Math.max(height.get(p), h));
                        h++;
                        p = parent.get(p);
                    }
                    children.get(q[1]).add(parent.size() - 1);
                }
            } else {
                int cur = q[1];
                int p = parent.get(q[1]);
                int h = 2;
                int max = height.get(cur);
                while (p != -1) {
                    max = Math.max(max, h - 1);
                    int i = 0;
                    for (int c : children.get(p)) {
                        if (c != cur) {
                            max = Math.max(max, height.get(c) + h);
                        }
//                        i++;
//                        if (i == 2) {
//                            break;
//                        }
                    }
                    h++;
                    cur = p;
                    p = parent.get(cur);
                }
                pw.println(max);
            }
        }
        pw.close();
    }

    public static void main(String[] args) throws FileNotFoundException {
        NewBarns prob = new NewBarns();
        prob.readFile();
        prob.solve();
    }

}
