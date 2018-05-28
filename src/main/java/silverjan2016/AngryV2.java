package silverjan2016;/*
ID: cravuri
LANG: JAVA
TASK: angry
*/

import java.io.*;
import java.util.*;

public class AngryV2 {

    static int[] balePos;
    static int maxBombs;

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("angry.in"));
        StringTokenizer st = new StringTokenizer(text.readLine());
        balePos = new int[Integer.parseInt(st.nextToken())];
        maxBombs = Integer.parseInt(st.nextToken());
        for (int i = 0; i < balePos.length; i++) {
            balePos[i] = Integer.parseInt(text.readLine());
        }
    }

    public static int minBlast() {
        if (maxBombs >= balePos.length) {
            return 0;
        }
        Arrays.sort(balePos);
        Pair[] pairs = new Pair[balePos.length - 1];
        for (int i = 0; i < pairs.length; i++) {
            Pair p = new Pair(balePos[i], balePos[i + 1]);
            pairs[i] = p;
        }
        Arrays.sort(pairs, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o2.getDifference() - o1.getDifference();
            }
        });
        int x = balePos[balePos.length - 1] - balePos[0];
        for (int i = 0; i < maxBombs; i++) {
            x = x - pairs[i].getDifference();
        }
        x = (int) Math.ceil(Math.ceil((x*1.0)/(maxBombs*(1.0)))/2.0);
        return x;
    }

    public static void writeFile(int minBlast) throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter("angry.out"));
        text.write(minBlast + "");
        text.newLine();
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        writeFile(minBlast());
    }

    static class Pair {
        int bale1;
        int bale2;

        public Pair(int bale1, int bale2) {
            if (bale1 < bale2) {
                this.bale1 = bale1;
                this.bale2 = bale2;
            } else {
                this.bale1 = bale2;
                this.bale2 = bale1;
            }
        }

        public int getDifference() {
            return Math.abs(this.bale1 - this.bale2);
        }
    }

}
