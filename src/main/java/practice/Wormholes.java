/*
ID: cravuri
LANG: JAVA
TASK: wormhole
*/

import java.io.*;
import java.util.*;

class wormhole {

    static Point[] wormholes;
    static int[] pairings;
    static int[] next;

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("wormhole.in"));
        wormholes = new Point[Integer.parseInt(text.readLine())];
        pairings = new int[wormholes.length];
        next = new int[wormholes.length];
        for (int i = 0; i < wormholes.length; i++) {
            StringTokenizer st = new StringTokenizer(text.readLine());
            Point p = new Point();
            p.x = Integer.parseInt(st.nextToken());
            p.y = Integer.parseInt(st.nextToken());
            wormholes[i] = p;
        }
        for (int i = 0; i < pairings.length; i++) {
            pairings[i] = -1;
        }
        for (int i = 0; i < next.length; i++) {
            next[i] = -1;
            for (int j = 0; j < next.length; j++) {
                if (wormholes[j].x > wormholes[i].x && wormholes[j].y == wormholes[i].y) {
                    if (next[i] == -1 || wormholes[j].x - wormholes[i].x < wormholes[next[i]].x - wormholes[i].x) {
                        next[i] = j;
                    }
                }
            }
        }
    }

    public static boolean cycleExists() {
        for (int start = 0; start < wormholes.length; start++) {
            int pos = start;
            for (Point wormhole : wormholes) {
                pos = next[pairings[pos]];
                if (pos == -1) {
                    break;
                }
            }
            if (pos != -1) {
                return true;
            }
        }
        return false;
    }

    public static int numOfPairings() {
        int i;
        int total = 0;
        for (i = 0; i < pairings.length; i++) {
            if (pairings[i] == -1) {
                break;
            }
        }
        if (i >= pairings.length) {
            if (cycleExists()) {
                return 1;
            } else {
                return 0;
            }
        }
        for (int j = i + 1; j < pairings.length; j++) {
            if (pairings[j] == -1) {
                pairings[i] = j;
                pairings[j] = i;
                total += numOfPairings();
                pairings[i] = -1;
                pairings[j] = -1;
            }
        }
        return total;
    }

    public static void writeFile(int x) throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter("wormhole.out"));
        text.write(x + "");
        text.newLine();
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        writeFile(numOfPairings());
    }

    static class Point {
        int x;
        int y;
    }

}
