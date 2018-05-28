import java.util.*;
import java.io.*;
import java.math.*;

/**
 * Auto-generated code below aims at helping you parse
 * the standard input according to the problem statement.
 **/
class Player {

    static int count(int i, int j, int[][] grid, int nA, int nB) {
        if (i < 0) {
            return -1;
        }
        int[][] dir = new int[][] {{1,0},{-1,0},{0,1},{0,-1}};
        int count = 0;
        for (int d = 0; d < 4; d++) {
            int ni = i + dir[d][0];
            int nj = j + dir[d][1];
            if (ni >= 0 && nj >= 0 && ni < 12 && nj < 6) {
                if (grid[ni--][nj] == nA) {
                    count++;
                }
            }
            if (ni >= 0 && nj >= 0 && ni < 12 && nj < 6) {
                if (grid[ni][nj] == nA) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        // game loop
        while (true) {
            int nA = -1, nB = -1;
            for (int i = 0; i < 8; i++) {
                int colorA = in.nextInt(); // color of the first block
                int colorB = in.nextInt(); // color of the attached block
                if (i == 0) {
                    nA = colorA;
                    nB = colorB;
                }
            }
            int score1 = in.nextInt();
            for (int i = 0; i < 12; i++) {
                String row = in.next();
            }
            int score2 = in.nextInt();
            int[][] grid = new int[12][6];
            for (int i = 0; i < 12; i++) {
                String row = in.next(); // One line of the map ('.' = empty, '0' = skull block, '1' to '5' = colored block)
                for (int j = 0; j < row.length(); j++) {
                    grid[i][j] = (row.charAt(j) == '.' ? -1 : row.charAt(j) - '0');
                }
            }
            int[] drop = new int[6];
            for (int j = 0; j < 6; j++) {
                drop[j] = -1;
                for (int i = 11; i >= 0; i--) {
                    if (grid[i][j] == -1) {
                        drop[j] = i;
                        break;
                    }
                }
            }
            int col = -1;
            int maxCount = -1;
            for (int i = 0; i < 6; i++) {
                int count = count(drop[i], i, grid, nA, nB);
                if (count > maxCount) {
                    maxCount = count;
                    col = i;
                }
            }

            System.out.println(col); // "x": the column in which to drop your blocks
        }
    }
}