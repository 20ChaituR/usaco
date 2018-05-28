package silverfeb2016;/*
ID: cravuri
LANG: JAVA
TASK: silverfeb2016.Balancing
*/

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Balancing {

    static int[] X;
    static int[] Y;
    static Set<int[]> allPossPoints = new HashSet<int[]>();

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("balancing.in"));
        int n = Integer.parseInt(text.readLine());
        X = new int[n];
        Y = new int[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(text.readLine());
            X[i] = Integer.parseInt(st.nextToken());
            Y[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static int minMax() {
        setAllPossPoints();
        int min = Integer.MAX_VALUE;
        for (int[] point : allPossPoints) {
            int x = maxQuad(point[0], point[1]);
            if (x < min) {
                min = x;
            }
        }
        return min;
    }

    public static int maxQuad(int a, int b) {
//        int a = 0;
//        for (int val : X) {
//            a += val;
//        }
//        a = 2 * Math.round((a/X.length)/2);
//        int b = 0;
//        for (int val : Y) {
//            b += val;
//        }
//        b = 2 * Math.round((b/Y.length)/2);
        int quad1 = 0;
        int quad2 = 0;
        int quad3 = 0;
        int quad4 = 0;
        for (int i = 0; i < X.length; i++) {
            if (X[i] > a) {
                if (Y[i] > b) {
                    quad1++;
                } else {
                    quad2++;
                }
            } else {
                if (Y[i] > b) {
                    quad4++;
                } else {
                    quad3++;
                }
            }
        }
        return max(quad1, max(quad2, max(quad3, quad4)));
    }

    public static void setAllPossPoints() {
        for (int i = 0; i < X.length; i++) {
            allPossPoints.add(new int[] {X[i] + 1, Y[i] + 1});
            allPossPoints.add(new int[] {X[i] - 1, Y[i] + 1});
            allPossPoints.add(new int[] {X[i] + 1, Y[i] - 1});
            allPossPoints.add(new int[] {X[i] - 1, Y[i] - 1});
        }
    }

    public static int max(int a, int b) {return a >= b ? a : b;}

    public static void writeFile(int min) throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter("balancing.out"));
        text.write(min + "");
        text.newLine();
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        writeFile(minMax());
    }

}
