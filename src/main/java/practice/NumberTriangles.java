/*
ID: cravuri
LANG: JAVA
TASK: numtri
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class numtri {

    static final int MIN = Integer.MIN_VALUE;
    static int[][] triangle;
    static int[][] memo;

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("numtri.in"));
        int n = Integer.parseInt(text.readLine());
        triangle = new int[n][n];
        memo = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                triangle[i][j] = MIN;
                memo[i][j] = -1;
            }
            String line = text.readLine();
            String[] a = line.split(" ");
            for (int j = 0; j < a.length; j++) {
                triangle[i][j] = Integer.parseInt(a[j]);
            }
        }
    }

    public static int highestSum(int row, int col) {
        if (memo[row][col] != -1) {
            return memo[row][col];
        }
        int sol;
        if (row == triangle.length - 1) {
            sol = triangle[row][col];
        } else if (col == triangle.length - 1) {
            sol = highestSum(row + 1, col) + triangle[row][col];
        } else {
            sol = Math.max(highestSum(row + 1, col), highestSum(row + 1, col + 1)) + triangle[row][col];
        }
        memo[row][col] = sol;
        return sol;
    }

    public static void writeFile() throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter("numtri.out"));
        text.write(highestSum(0, 0) + "");
        text.newLine();
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        writeFile();
    }

}
