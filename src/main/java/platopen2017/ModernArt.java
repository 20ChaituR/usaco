import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

public class ModernArt {

    int N;
    int[][] canvas;
    HashMap<Integer, int[]> rectangles = new HashMap<>();
    boolean[] bottom;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("art.in"));
        N = sc.nextInt();
        canvas = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                canvas[i][j] = sc.nextInt() - 1;
            }
        }
    }

    void updateRectangle(int x, int i, int j) {
        if (rectangles.containsKey(x)) {
            int[] a = rectangles.get(x);
            a = new int[] {Math.min(a[0], i), Math.max(a[1], i), Math.min(a[2], j), Math.max(a[3], j)};
            rectangles.put(x, a);
        } else {
            rectangles.put(x, new int[] {i, i, j, j});
        }
    }

    void solve() throws IOException {
        bottom = new boolean[N*N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (canvas[i][j] == -1) {
                    continue;
                }
                updateRectangle(canvas[i][j], i, j);
            }
        }
        for (int k = 0; k < N*N; k++) {
            if (rectangles.containsKey(k)) {
                int[] dimensions = rectangles.get(k);
                for (int i = dimensions[0]; i <= dimensions[1]; i++) {
                    for (int j = dimensions[2]; j <= dimensions[3]; j++) {
                        if (canvas[i][j] != k) {
                            bottom[canvas[i][j]] = true;
                        }
                    }
                }
            }
        }
        int num = 0;
        for (int i = 0; i < bottom.length; i++) {
            if (!bottom[i]) {
                num++;
            }
        }
        PrintWriter pw = new PrintWriter(new FileWriter("art.out"));
        pw.println(num);
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        ModernArt prob = new ModernArt();
        prob.readFile();
        prob.solve();
    }

}
