package silverjan2016;/*
ID: cravuri
LANG: JAVA
TASK: gates
*/

import java.io.*;
import java.util.Objects;

public class Gates {

    static int numOfGroups;
    static String[][] farm;
    static boolean legit;

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("gates.in"));
        int n = Integer.parseInt(text.readLine()); // Don't delete this
        String path = text.readLine();
        int minX = 0;
        int maxX = 0;
        int minY = 0;
        int maxY = 0;
        int currX = 0;
        int currY = 0;
        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == 'N') {
                currY++;
                if (currY > maxY) {
                    maxY = currY;
                }
            } else if (path.charAt(i) == 'S') {
                currY--;
                if (currY < minY) {
                    minY = currY;
                }
            } else if (path.charAt(i) == 'E') {
                currX++;
                if (currX > maxX) {
                    maxX = currX;
                }
            } else if (path.charAt(i) == 'W') {
                currX--;
                if (currX < minX) {
                    minX = currX;
                }
            }
        }
        minY = Math.abs(minY);
        minX = Math.abs(minX);
        farm = new String[2*maxY + 2*minY + 1][2*maxX + 2*minX + 1];
        for (int i = 0; i < 2*maxY + 2*minY + 1; i++) {
            for (int j = 0; j < 2*maxX + 2*minX + 1; j++) {

            }
        }
        int currRow = 2*maxY;
        int currCol = 2*minX;
        farm[currRow][currCol] = "#";
        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == 'N') {
                farm[currRow - 1][currCol] = "#";
                farm[currRow - 2][currCol] = "#";
                currRow = currRow - 2;
            } else if (path.charAt(i) == 'S') {
                farm[currRow + 1][currCol] = "#";
                farm[currRow + 2][currCol] = "#";
                currRow = currRow + 2;
            } else if (path.charAt(i) == 'E') {
                farm[currRow][currCol + 1] = "#";
                farm[currRow][currCol + 2] = "#";
                currCol = currCol + 2;
            } else if (path.charAt(i) == 'W') {
                farm[currRow][currCol - 1] = "#";
                farm[currRow][currCol - 2] = "#";
                currCol = currCol - 2;
            }
        }
    }

    public static void flood(int currRow, int currCol, String oldColor, String newColor) {
        if (currRow < 0 || currRow >= farm.length || currCol < 0 || currCol >= farm[0].length) {
            legit = false;
            return;
        }
        if (Objects.equals(farm[currRow][currCol], newColor)) {
            return;
        }
        farm[currRow][currCol] = newColor;
        flood(currRow + 1, currCol, oldColor, newColor);
        flood(currRow, currCol + 1, oldColor, newColor);
        flood(currRow - 1, currCol, oldColor, newColor);
        flood(currRow, currCol - 1, oldColor, newColor);
    }

    public static void countSections() {
        legit = true;
        for (int i = 0; i < farm.length; i++) {
            for (int j = 0; j < farm[0].length; j++) {
                if (!Objects.equals(farm[i][j], "#")) {
                    flood(i, j, "", "#");
                    if (legit) {
                        numOfGroups++;
                    }
                    legit = true;
                }
            }
        }
    }

    public static void writeFile() throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter("gates.out"));
        text.write(numOfGroups + "");
        text.newLine();
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        countSections();
        writeFile();
    }

}
