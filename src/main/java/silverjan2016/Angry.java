package silverjan2016;/*
ID: cravuri
LANG: JAVA
TASK: silverjan2016.AngryV2
*/

import java.io.*;
import java.util.*;

public class Angry {
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

    public static int minBlast(int numOfBombs, Set<Pair> placing, Set<Pair> blanks) {
        if (numOfBombs == maxBombs) {
            int minDifference = 0;
            for (Pair place : placing) {
                if (place.getDifference() > minDifference) {
                    minDifference = place.getDifference();
                }
            }
            return minDifference;
        }
        Set<Pair> places = new HashSet<>();
        Set<Pair> blanking = new HashSet<>();
        equals(places, placing);
        equals(blanking, blanks);
        List<Integer> minBlasts = new ArrayList<>();
        for (Pair blank : blanks) {
            for (Pair place : placing) {
                if (blank.bale1 >= place.bale1 && blank.bale2 <= place.bale2) {
                    places.add(new Pair(place.bale1, blank.bale1));
                    places.add(new Pair(blank.bale2, place.bale2));
                    places.remove(place);
                    blanking.remove(blank);
                    break;
                }
            }
            minBlasts.add(minBlast(numOfBombs + 1, places, blanking));
        }
        return minElement(minBlasts);
    }

    public static int minElement(List<Integer> a) {
        int x = Integer.MAX_VALUE;
        for (Integer element : a) {
            if (element < x) {
                x = element;
            }
        }
        return x;
    }

    public static void equals(Set<Pair> a, Set<Pair> b) {
        for (Pair p : b) {
            a.add(p);
        }
    }

    public static int minDifference() {
        Arrays.sort(balePos);
        Set<Pair> blanks = new HashSet<>();
        for (int i = 0; i < balePos.length - 1; i++) {
            Pair p = new Pair(balePos[i], balePos[i + 1]);
            blanks.add(p);
        }
        Set<Pair> placing = new HashSet<>();
        placing.add(new Pair(balePos[0], balePos[balePos.length - 1]));
        return (int) Math.ceil((minBlast(1, placing, blanks)*1.0)/2.0);
    }

    public static void writeFile(int minBlast) throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter("angry.out"));
        text.write(minBlast + "");
        text.newLine();
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        writeFile(minDifference());
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
