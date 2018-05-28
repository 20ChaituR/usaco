import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class LasersAndMirrors {

    // ^ > \/ <

    int N;
    Point start;
    Point end;
    Point[] mirrorsx;
    Point[] mirrorsy;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("lasers.in"));
        N = sc.nextInt();
        int x = sc.nextInt();
        int y = sc.nextInt();
        start = new Point(x, y);
        x = sc.nextInt();
        y = sc.nextInt();
        end = new Point(x, y);
        mirrorsx = new Point[N + 2];
        mirrorsy = new Point[N + 2];
        mirrorsx[0] = start;
        mirrorsy[0] = start;
        for (int i = 1; i <= N; i++) {
            x = sc.nextInt();
            y = sc.nextInt();
            mirrorsx[i] = new Point(x, y);
            mirrorsy[i] = new Point(x, y);
        }
        mirrorsx[N + 1] = end;
        mirrorsy[N + 1] = end;
        Arrays.sort(mirrorsx, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.x == o2.x ? o1.y - o2.y : o1.x - o2.x;
            }
        });
        Arrays.sort(mirrorsy, new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return o1.y == o2.y ? o1.x - o2.x : o1.y - o2.y;
            }
        });
    }

    Point next(Point p, int d) {
        if (d == 0) {
            int l = 0;
            int r = mirrorsx.length - 1;
            int min = -1;
            boolean found = false;
            while (l <= r) {
                min = (l + r) / 2;
                if (mirrorsx[min].x > p.x) {
                    r = min - 1;
                } else if (mirrorsx[min].x < p.x) {
                    l = min + 1;
                } else {
                    if (mirrorsx[min].y > p.y) {
                        if (mirrorsx[min - 1].y == p.y) {
                            return mirrorsx[min];
                        }
                        found = true;
                        r = min - 1;
                    } else if (mirrorsx[min].y <= p.y) {
                        l = min + 1;
                    }
                }
            }
            if (!found) {
                return new Point(-1, -1);
            }
            return mirrorsx[min];
        }
        if (d == 2) {
            int l = 0;
            int r = mirrorsx.length - 1;
            int max = -1;
            boolean found = false;
            while (l <= r) {
                max = (l + r) / 2;
                if (mirrorsx[max].x > p.x) {
                    r = max - 1;
                } else if (mirrorsx[max].x < p.x) {
                    l = max + 1;
                } else {
                    if (mirrorsx[max].y >= p.y) {
                        r = max - 1;
                    } else if (mirrorsx[max].y < p.y) {
                        if (mirrorsx[max + 1].y == p.y) {
                            return mirrorsx[max];
                        }
                        found = true;
                        l = max + 1;
                    }
                }
            }
            if (!found) {
                return new Point(-1, -1);
            }
            return mirrorsx[max];
        }
        if (d == 1) {
            int l = 0;
            int r = mirrorsy.length - 1;
            int min = -1;
            boolean found = false;
            while (l <= r) {
                min = (l + r) / 2;
                if (mirrorsy[min].y > p.y) {
                    r = min - 1;
                } else if (mirrorsy[min].y < p.y) {
                    l = min + 1;
                } else {
                    if (mirrorsy[min].x > p.x) {
                        if (mirrorsy[min - 1].x == p.x) {
                            return mirrorsy[min];
                        }
                        found = true;
                        r = min - 1;
                    } else if (mirrorsy[min].x <= p.x) {
                        l = min + 1;
                    }
                }
            }
            if (!found) {
                return new Point(-1, -1);
            }
            return mirrorsy[min];
        }
        if (d == 3) {
            int l = 0;
            int r = mirrorsy.length - 1;
            int max = -1;
            boolean found = false;
            while (l <= r) {
                max = (l + r) / 2;
                if (mirrorsy[max].y > p.y) {
                    r = max - 1;
                } else if (mirrorsy[max].y < p.y) {
                    l = max + 1;
                } else {
                    if (mirrorsy[max].x >= p.x) {
                        r = max - 1;
                    } else if (mirrorsy[max].x < p.x) {
                        if (mirrorsy[max + 1].x == p.x) {
                            return mirrorsy[max];
                        }
                        found = true;
                        l = max + 1;
                    }
                }
            }
            if (!found) {
                return new Point(-1, -1);
            }
            return mirrorsy[max];
        }
        return new Point(-1, -1);
    }

    void solve() throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("lasers.out"));
        Deque<int[]> dq = new ArrayDeque<>();
        for (int d = 0; d < 4; d++) {
            dq.add(new int[]{start.x, start.y, d, 0});
        }
        Set<Point> vis = new HashSet<>();
        while (!dq.isEmpty()) {
            int[] cur = dq.removeFirst();
            Point n = next(new Point(cur[0], cur[1]), cur[2]);
            if (n.x == -1) {
                continue;
            }
            if (vis.contains(n)) {
                continue;
            }
            vis.add(n);
            if (n.equals(end)) {
                pw.println(cur[3]);
                pw.close();
                return;
            }
            for (int d = 0; d < 4; d++) {
                if (d == (cur[2] + 2) % 4) {
                    continue;
                }
                if (d == cur[2]) {
                    dq.addFirst(new int[]{n.x, n.y, d, cur[3]});
                } else {
                    dq.addLast(new int[]{n.x, n.y, d, cur[3] + 1});
                }
            }
        }
        pw.println(-1);
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        LasersAndMirrors prob = new LasersAndMirrors();
        prob.readFile();
        prob.solve();
    }

    class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Point point = (Point) o;

            if (x != point.x) return false;
            return y == point.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }

}
