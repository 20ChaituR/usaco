/*
ID: cravuri
LANG: JAVA
TASK: castle
*/

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class castle {

    static boolean[][][] walls;
    static int[][] rooms;
    static int[] areas;
    static int numOfRooms;
    static int maxArea;
    static int N;
    static int M;
    static int maxRemovedArea;
    static int[] bestWall = new int[] {-1, };

    public static void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("castle.in"));
        N = sc.nextInt();
        M = sc.nextInt();
        walls = new boolean[M][N][4];
        rooms = new int[M][N];
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                int x = sc.nextInt();
                for (int d = 0; d < 4; d++) {
                    walls[r][c][d] = (x % 2 == 1);
                    x = x / 2;
                }
                rooms[r][c] = -1;
            }
        }
    }

    public static void floodFill(int r, int c) {
        if (r < 0 || r >= rooms.length || c < 0 || c >= rooms[0].length) {
            return;
        }
        if (rooms[r][c] != -1) {
            return;
        }
        rooms[r][c] = numOfRooms;
        boolean[] wall = walls[r][c];
        if (!wall[0]) {
            floodFill(r, c - 1);
        }
        if (!wall[1]) {
            floodFill(r - 1, c);
        }
        if (!wall[2]) {
            floodFill(r, c + 1);
        }
        if (!wall[3]) {
            floodFill(r + 1, c);
        }
    }

    public static void paintRooms() {
        numOfRooms = 0;
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                if (rooms[r][c] == -1) {
                    floodFill(r, c);
                    numOfRooms++;
                }
            }
        }
    }

    public static void measureRooms() {
        areas = new int[numOfRooms];
        for (int r = 0; r < M; r++) {
            for (int c = 0; c < N; c++) {
                areas[rooms[r][c]]++;
            }
        }
        maxArea = 0;
        for (int n = 0; n < numOfRooms; n++) {
            if (areas[n] > maxArea) {
                maxArea = areas[n];
            }
        }
    }

    public static void update(int r, int c, int k1, int k2, int d) {
        int p;
        if (k1 == k2) {
            p = areas[k1];
        } else {
            p = areas[k1] + areas[k2];
        }
        if (p > maxRemovedArea) {
            maxRemovedArea = p;
            bestWall = new int[]{r, c, d};
        } else if (p == maxRemovedArea) {
            if (c < bestWall[1]) {
                maxRemovedArea = p;
                bestWall = new int[]{r, c, d};
            } else if (c == bestWall[1]) {
                if (r > bestWall[0]) {
                    maxRemovedArea = p;
                    bestWall = new int[]{r, c, d};
                } else if (r == bestWall[0]) {
                    if (d < bestWall[2]) {
                        maxRemovedArea = p;
                        bestWall = new int[]{r, c, d};
                    }
                }
            }
        }
    }

    public static void bestWall() {
        maxRemovedArea = 0;
        for (int r = 0; r < M; r++) {
            for (int c = N - 1; c >= 0; c--) {
                if (r != 0 && walls[r][c][1]) {
                    update(r, c, rooms[r][c], rooms[r - 1][c], 1);
                }
                if (c != N - 1 && walls[r][c][2]) {
                    update(r, c, rooms[r][c], rooms[r][c + 1], 2);
                }
            }
        }
    }

    public static void writeFile() throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter("castle.out"));
        String[] directions = new String[] {"W", "N", "E", "S"};
        text.write(numOfRooms + "");
        text.newLine();
        text.write(maxArea + "");
        text.newLine();
        text.write(maxRemovedArea + "");
        text.newLine();
        text.write((bestWall[0] + 1) + " " + (bestWall[1] + 1) + " " + directions[bestWall[2]]);
        text.newLine();
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        paintRooms();
        measureRooms();
        bestWall();
        writeFile();
    }

}
