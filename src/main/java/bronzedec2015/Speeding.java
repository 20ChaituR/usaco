package bronzedec2015;/*
ID: cravuri
LANG: JAVA
TASK: bronzedec2015.Speeding
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Speeding {

    static List<int[]> limits = new ArrayList<>();
    static List<int[]> speeds = new ArrayList<>();
//    private static final String directory = "/Users/cravuri/Documents/";
//    private static final String fileSuffix = ".txt";
    private static final String directory = "";
    private static final String fileSuffix = "";


    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader(directory + "speeding.in" + fileSuffix));
        StringTokenizer st = new StringTokenizer(text.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(text.readLine());
            int length = Integer.parseInt(st.nextToken());
            int limit = Integer.parseInt(st.nextToken());
            int[] a = {length, limit};
            limits.add(a);
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(text.readLine());
            int length = Integer.parseInt(st.nextToken());
            int limit = Integer.parseInt(st.nextToken());
            int[] a = {length, limit};
            speeds.add(a);
        }
    }

    public static int maxSpeed() {
        int n = 0;
        int x = 1;
        int m = 0;
        int y = 1;
        int max = 0;
        for (int i = 1; i <= 100; i++) {
            if (x > limits.get(n)[0]) {
                n++;
                x = 1;
            }
            if (y > speeds.get(m)[0]) {
                m++;
                y = 1;
            }
            if (speeds.get(m)[1] - limits.get(n)[1] > max) {
                max = speeds.get(m)[1] - limits.get(n)[1];
            }
            x++;
            y++;
        }
        return max;
    }

    public static void writeFile(int max) throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter(directory + "speeding.out" + fileSuffix));
        text.write(max + "");
        text.newLine();
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        writeFile(maxSpeed());
    }
}
