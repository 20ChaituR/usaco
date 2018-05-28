import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Knapsack {

    static int[] sizes;
    static int[] values;
    static int maxCapacity;

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(text.readLine());
        int n = Integer.parseInt(st.nextToken());
        maxCapacity = Integer.parseInt(st.nextToken());
        sizes = new int[n];
        values = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(text.readLine());
            sizes[i] = Integer.parseInt(st.nextToken());
            values[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static int maxVal() {
        int[][] f = new int[values.length + 1][maxCapacity + 1];
        for (int x = 0; x < f[0].length; x++) {
            f[0][x] = 0;
        }
        for (int i = 1; i < f.length; i++) {
            for (int w = 0; w < f[0].length; w++) {
                f[i][w] = f[i - 1][w];
                if (w - sizes[i - 1] >= 0) {
                    f[i][w] = Math.max(f[i][w], f[i - 1][w - sizes[i - 1]] + values[i - 1]);
                }
            }
        }
        return f[values.length][maxCapacity];
    }

    public static void main(String[] args) throws IOException {
        readFile();
        System.out.println(maxVal());
    }

}
