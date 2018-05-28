import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class LotsOfTriangles {

    int N;
    long[][] trees;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("triangles.in"));
        N = sc.nextInt();
        trees = new long[N][2];
        for (int i = 0; i < N; i++) {
            trees[i][0] = sc.nextInt();
            trees[i][1] = sc.nextInt();
        }
    }

    boolean isInTriangle(long x1, long y1, long x2, long y2, long x3, long y3, long x, long y) {
        long ABC = Math.abs (x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2));
        long ABP = Math.abs (x1 * (y2 - y) + x2 * (y - y1) + x * (y1 - y2));
        long APC = Math.abs (x1 * (y - y3) + x * (y3 - y1) + x3 * (y1 - y));
        long PBC = Math.abs (x * (y2 - y3) + x2 * (y3 - y) + x3 * (y - y2));
        return ABP + APC + PBC == ABC;
    }

    void solve() throws IOException {
        int[] values = new int[N - 2];
        for (int i = 0; i < trees.length; i++) {
            for (int j = i + 1; j < trees.length; j++) {
                for (int k = j + 1; k < trees.length; k++) {
                    long x1 = trees[i][0];
                    long y1 = trees[i][1];
                    long x2 = trees[j][0];
                    long y2 = trees[j][1];
                    long x3 = trees[k][0];
                    long y3 = trees[k][1];
                    int v = 0;
                    for (int p = 0; p < trees.length; p++) {
                        if (p != i && p != j && p != k) {
                            if (isInTriangle(x1, y1, x2, y2, x3, y3, trees[p][0], trees[p][1])) {
                                v++;
                            }
                        }
                    }
                    values[v]++;
                }
            }
        }
        PrintWriter pw = new PrintWriter(new FileWriter("triangles.out"));
        for (int i = 0; i < values.length; i++) {
            pw.println(values[i]);
        }
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        LotsOfTriangles prob = new LotsOfTriangles();
        prob.readFile();
        prob.solve();
    }

}
