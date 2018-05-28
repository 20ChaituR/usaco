package silveropen2016;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DiamondCollectorV2 {

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
        int max = -1;
        int k = -1;
        for (int i = 0; i < selections.length; i++) {
            if (size(selections[i]) > max) {
                max = size(selections[i]);
                k = i;
            }
        }
        max = -1;
        int total = 0;
        for (int[] selection : selections) {
            if (isDisjoint(selections[k], selection)) {
                if (size(selection) > max) {
                    max = size(selection);
                    total = size(selection) + size(selections[k]);
                }
            }
        }
        return total;
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
