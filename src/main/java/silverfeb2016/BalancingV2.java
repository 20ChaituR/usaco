package silverfeb2016;/*
ID: cravuri
LANG: JAVA
TASK: silverfeb2016.Balancing
*/

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BalancingV2 {

    static int[] X;
    static int[] Y;
    static int[] actualX;
    static int[] actualY;

    static Set<Integer> allX = new HashSet<>();
    static Set<Integer> allY = new HashSet<>();

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("balancing.in"));
        int n = Integer.parseInt(text.readLine());
        X = new int[n];
        Y = new int[n];
        actualX = new int[n];
        actualY = new int[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(text.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            X[i] = x;
            actualX[i] = x;
            Y[i] = y;
            actualY[i] = y;
        }
    }

    public static int minMax() {
        setAllPossPoints();
        int min = Integer.MAX_VALUE;
        for (int a : allX) {
            for (int b : allY) {
                int x = maxQuad(a, b);
                if (x < min) {
                    min = x;
                }
            }
        }
        return min;
    }

    public static int maxQuad(int a, int b) {
        int quad1 = 0;
        int quad2 = 0;
        int quad3 = 0;
        int quad4 = 0;
        for (int i = 0; i < X.length; i++) {
            if (actualX[i] > a) {
                if (actualY[i] > b) {
                    quad1++;
                } else {
                    quad2++;
                }
            } else {
                if (actualY[i] > b) {
                    quad4++;
                } else {
                    quad3++;
                }
            }
        }
        return max(quad1, max(quad2, max(quad3, quad4)));
    }

    public static void setAllPossPoints() {
        Arrays.sort(X);
        Arrays.sort(Y);
        for (int i = 0; i < X.length; i++) {
            allX.add(X[i] + 1);
            allY.add(Y[i] + 1);
        }
        allX.add(X[0] - 1);
        allY.add(Y[0] - 1);
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
