/*
ID: cravuri
LANG: JAVA
TASK: barn1
*/

import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

class barn1 {

    static int[] occupiedStalls;
    static int totalStalls;
    static int maxBoards;

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("barn1.in"));
        StringTokenizer st = new StringTokenizer(text.readLine());
        maxBoards = Integer.parseInt(st.nextToken());
        totalStalls = Integer.parseInt(st.nextToken());
        occupiedStalls = new int[Integer.parseInt(st.nextToken())];
        for (int i = 0; i < occupiedStalls.length; i++) {
            occupiedStalls[i] = Integer.parseInt(text.readLine());
        }
    }

    public static int minStalls() {
        Arrays.sort(occupiedStalls);
        Pair[] pairs = new Pair[occupiedStalls.length - 1];
        for (int i = 0; i < pairs.length; i++) {
            Pair p = new Pair(occupiedStalls[i], occupiedStalls[i + 1]);
            pairs[i] = p;
        }
        Arrays.sort(pairs, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o2.getDifference() - o1.getDifference();
            }
        });
        int x = 0;
        if (maxBoards >= occupiedStalls.length) {
            return occupiedStalls.length;
        }
        for (int i = 0; i <= maxBoards - 2; i++) {
            x = x + pairs[i].getDifference();
        }
        return occupiedStalls[occupiedStalls.length - 1] - occupiedStalls[0] - x + 1;
    }

    public static void writeFile(int minStalls) throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter("barn1.out"));
        text.write(minStalls + "");
        text.newLine();
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        writeFile(minStalls());
    }

    static class Pair {
        int stall1;
        int stall2;

        public Pair(int stall1, int stall2) {
            this.stall1 = stall1;
            this.stall2 = stall2;
        }

        public int getDifference() {
            return Math.abs(this.stall1 - this.stall2) - 1;
        }
    }

}
