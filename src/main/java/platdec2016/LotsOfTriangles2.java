import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class LotsOfTriangles2 {

    int N;
    Point[] trees;
    int[][][] triangles;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("triangles.in"));
        N = sc.nextInt();
        trees = new Point[N];
        triangles = new int[N][N][N];
        for (int i = 0; i < N; i++) {
            trees[i] = new Point(sc.nextInt(), sc.nextInt(), i);
        }
    }

    void add(int i1, int i2, int i3) {
        triangles[i1][i2][i3]++;
//        triangles[i1][i3][i2]++;
//        triangles[i2][i1][i3]++;
//        triangles[i2][i3][i1]++;
//        triangles[i3][i2][i1]++;
//        triangles[i3][i1][i2]++;
    }

    void solve() throws IOException {
        for (int i = 0; i < trees.length; i++) {
            for (int j = 0; j < trees.length; j++) {
                if (j != i) {
                    trees[j].angle = Math.atan((trees[j].y - trees[i].y) / (trees[j].x - trees[i].x));
                    if (trees[j].x < trees[i].x) {
                        trees[j].angle += Math.PI;
                    }
                    trees[j].angle = (trees[j].angle + 2 * Math.PI) % (2 * Math.PI);
                }
            }
            List<Point> lines = new ArrayList<>();
            for (int j = 0; j < trees.length; j++) {
                if (i == j) {
                    continue;
                }
                lines.add(trees[j]);
                Point p = new Point(-1, -1, -trees[j].ind - 1);
                p.angle = (Math.PI + trees[j].angle) % (2 * Math.PI);
                lines.add(p);
            }
            Collections.sort(lines);
            for (int j = 0; j < lines.size(); j++) {
                if (lines.get(j).ind >= 0) {
                    continue;
                }
                List<Point> p = new ArrayList<>();
                int curLine = j;
                int ind = -lines.get(j).ind - 1;
                for (int k = j + 1; k != j; k++) {
                    if (k == lines.size()) {
                        k = 0;
                    }
                    if (k == j) {
                        break;
                    }
                    if ((lines.get(k).angle - lines.get(j).angle) % (2 * Math.PI) >= 180) {
                        break;
                    }
                    if (lines.get(k).ind >= 0) {
                        p.add(new Point(curLine, -1, lines.get(k).ind));
                    } else {
                        curLine = k;
                        for (int n = 0; n < p.size(); n++) {
                            add(p.get(n).ind, ind, -lines.get((int)p.get(n).x).ind - 1);
                        }
                    }
                }
            }
        }
        int[] values = new int[N - 2];
        for (int i = 0; i < triangles.length; i++) {
            for (int j = i + 1; j < triangles.length; j++) {
                for (int k = j + 1; k < triangles.length; k++) {
                    values[triangles[i][j][k] / 3]++;
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
        LotsOfTriangles2 prob = new LotsOfTriangles2();
        prob.readFile();
        prob.solve();
    }

    class Point implements Comparator<Point>, Comparable<Point> {
        double x;
        double y;
        double angle = 0;
        int ind;

        public Point(int x, int y, int ind) {
            this.x = x;
            this.y = y;
            this.ind = ind;
        }

        @Override
        public int compareTo(Point o) {
            return (int) (100000000 * (angle - o.angle));
        }

        @Override
        public int compare(Point o1, Point o2) {
            return (int) (100000000 * (o1.angle - o2.angle));
        }
    }

}
