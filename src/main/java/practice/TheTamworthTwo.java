/*
ID: cravuri
LANG: JAVA
TASK: ttwo
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

class ttwo {

    static int fx;
    static int fy;
    static int fd;
    static int cx;
    static int cy;
    static int cd;
    static boolean[][] obstacles = new boolean[10][10];
    static int[] dx = {-1, 0, 1, 0}; // N, E, S, W
    static int[] dy = {0, 1, 0, -1}; // N, E, S, W

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("ttwo.in"));
        for (int i = 0; i < 10; i++) {
            String line = text.readLine();
            for (int j = 0; j < 10; j++) {
                String x = line.charAt(j) + "";
                if (x.equals("*")) {
                    obstacles[i][j] = true;
                }
                if (x.equals("F")) {
                    fx = i;
                    fy = j;
                }
                if (x.equals("C")) {
                    cx = i;
                    cy = j;
                }
            }
        }
    }

    public static int solve() {
        boolean found = false;
        int i;
        fd = 0;
        cd = 0;
        for (i = 0; i < 300; i++) {
            if (fx == cx && fy == cy) {
                found = true;
                break;
            }
            move();
        }
        if (found) {
            return i;
        } else {
            return 0;
        }
    }

    public static void move() {
        int x = fx + dx[fd];
        int y = fy + dy[fd];
        if (x < 0 || y < 0 || x >= 10 || y >= 10 || obstacles[x][y]) {
            fd = (fd + 1) % 4;
        } else {
            fx = x;
            fy = y;
        }
        x = cx + dx[cd];
        y = cy + dy[cd];
        if (x < 0 || y < 0 || x >= 10 || y >= 10 || obstacles[x][y]) {
            cd = (cd + 1) % 4;
        } else {
            cx = x;
            cy = y;
        }
    }

    public static void writeFile(int x) throws IOException {
        PrintWriter pr = new PrintWriter(new FileWriter("ttwo.out"));
        pr.println(x);
        pr.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        writeFile(solve());
    }

}
