/*
ID: cravuri
LANG: JAVA
TASK: transform
*/

import java.io.*;
import java.util.Arrays;

/**
 * 8/9/15
 */

class transform {

    static String[][] original;
    static String[][] transformed;

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("transform.in"));
        int n = Integer.parseInt(text.readLine());
        original = new String[n][n];
        transformed = new String[n][n];
        for (int i = 0; i < n; i++) {
            String line = text.readLine();
            for (int j = 0; j < line.length(); j++) {
                original[i][j] = String.valueOf(line.charAt(j));
            }
        }
        for (int i = 0; i < n; i++) {
            String line = text.readLine();
            for (int j = 0; j < line.length(); j++) {
                transformed[i][j] = String.valueOf(line.charAt(j));
            }
        }
    }

    public static boolean equals(String[][] a, String[][] b) {
        if (a.length != b.length || a[0].length != b[0].length) {
            return false;
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (!a[i][j].equals(b[i][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    public static String[][] ninetyRotate(String[][] a) {
        String[][] b = new String[a.length][a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                b[i][j] = a[a.length - j - 1][i];
            }
        }
        return b;
    }

    public static String[][] reflect(String[][] a) {
        String[][] b = new String[a.length][a.length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a.length; j++) {
                b[i][j] = a[i][a.length - 1 - j];
            }
        }
        return b;
    }

    public static int transformation() {
        if (equals(ninetyRotate(original), transformed)) {
            return 1;
        } else if (equals(ninetyRotate(ninetyRotate(original)), transformed)) {
            return 2;
        } else if (equals(ninetyRotate(ninetyRotate(ninetyRotate(original))), transformed)) {
            return 3;
        } else if (equals(reflect(original), transformed)) {
            return 4;
        } else if (equals(ninetyRotate(reflect(original)), transformed)) {
            return 5;
        } else if (equals(ninetyRotate(ninetyRotate(reflect(original))), transformed)) {
            return 5;
        } else if (equals(ninetyRotate(ninetyRotate(ninetyRotate(reflect(original)))), transformed)) {
            return 5;
        } else if (equals(original, transformed)) {
            return 6;
        } else {
            return 7;
        }
    }

    public static void writeFile(String x) throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter("transform.out"));
        text.write(x);
        text.newLine();
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        writeFile(Integer.toString(transformation()));
//        writeFile(Arrays.deepToString(original));
//        writeFile(Arrays.deepToString(transformed));
        System.exit(0);
    }
}
