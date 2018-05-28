package silveropen2016;

import java.io.*;
import java.util.*;

public class DiamondCollectorV3 {

    static int[] sizes;
    static int K;

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("diamond.in"));
        StringTokenizer st = new StringTokenizer(text.readLine());
        sizes = new int[Integer.parseInt(st.nextToken())];
        K = Integer.parseInt(st.nextToken());
        for (int i = 0; i < sizes.length; i++) {
            sizes[i] = Integer.parseInt(text.readLine());
        }
        Arrays.sort(sizes);
    }

    public static int findSelections() {
        int[][] selections = new int[sizes.length][2];
        for (int i = 0; i < sizes.length; i++) {
            int j = i;
            while (j < sizes.length) {
                if (sizes[j] - sizes[i] > K) {
                    j = j - 1;
                    break;
                }
                j++;
            }
            selections[i] = new int[] {i, j};
        }
        int max = 0;
        Arrays.sort(selections, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return size(o2) - size(o1);
            }
        });
        int total = size(selections[0]);
        for (int i = 1; i < selections.length; i++) {
            if (isDisjoint(selections[0], selections[i])) {

            }
        }
        for (int i = 0; i < selections.length - 1; i++) {
            for (int j = i + 1; j < selections.length; j++) {
                int x;
                if (isDisjoint(selections[i], selections[j])) {
                    x = size(selections[i]) + size(selections[j]);
                    if (x > max) {
                        max = x;
                    }
                }
            }
        }
        return max;
    }

    public static boolean isDisjoint(int[] a, int[] b) {
        return b[0] > a[1];
    }

    public static int size(int[] a) {
        return a[1] - a[0] + 1;
    }

    public static void writeFile(int max) throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter("diamond.out"));
        text.write(max + "");
        text.newLine();
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        writeFile(findSelections());
    }
}
