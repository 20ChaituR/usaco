package practice.usaco2011;

import java.util.*;

/**
 * Created on 12/21/15.
 */
public class CowBeautyPageant {

    static String[][] field;
    static List<Integer> paths = new ArrayList<>();

    public static void smallestPaint() {

    }

    public static void findPath(int row, int col, int length, Set<int[]> visited) {
        if (row >= field.length || row < 0 || col >= field[0].length || col < 0) {
            return;
        }
        if (visited.contains(new int[] {row, col})) {
            return;
        }
        if (field[row][col] == "2") {
            paths.add(length);
        }
        visited.add(new int[] {row, col});
        findPath(row + 1, col + 1, length + 1, visited);
        findPath(row + 1, col - 1, length + 1, visited);
        findPath(row - 1, col + 1, length + 1, visited);
        findPath(row - 1, col - 1, length + 1, visited);
    }

}
