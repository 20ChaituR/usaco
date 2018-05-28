package practice.usaco2011;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created on 12/21/15.
 */
public class MooSick {

    static int[] music;
    static int[] chord;
    static List<Integer> indexes = new ArrayList<>();
//    private static final String directory = "/Users/cravuri/Documents/";
//    private static final String fileSuffix = ".txt";
    private static final String directory = "";
    private static final String fileSuffix = "";


    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader(directory + "moosick.in" + fileSuffix));
        int musicLength = Integer.parseInt(text.readLine());
        music = new int[musicLength];
        for (int i = 0; i < musicLength; i++) {
            music[i] = Integer.parseInt(text.readLine());
        }
        int chordLength = Integer.parseInt(text.readLine());
        chord = new int[chordLength];
        for (int i = 0; i < chordLength; i++) {
            chord[i] = Integer.parseInt(text.readLine());
        }
    }

    public static void chords() {
        for (int i = 0; i <= music.length - chord.length; i++) {
            int[] currChord = new int[chord.length];
            System.arraycopy(music, i, currChord, 0, i + chord.length - i);
            if (ruminant(currChord)) {
                indexes.add(i + 1);
            }
        }
    }

    public static boolean ruminant(int[] potChord) {
        int[] rum = new int[chord.length];
        equals(rum, chord);
        int[] pot = new int[chord.length];
        equals(pot, potChord);
        Arrays.sort(pot);
        Arrays.sort(rum);
        int rumNum = rum[0];
        int potNum = pot[0];
        for (int i = 0; i < chord.length; i++) {
            pot[i] = pot[i] - potNum;
            rum[i] = rum[i] - rumNum;
        }
        return Arrays.equals(pot, rum);
    }

    public static void equals(int[] a, int[] b) {
        System.arraycopy(b, 0, a, 0, b.length);
    }

    public static void writeFile() throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter("moosick.out"));
        text.write(indexes.size() + "");
        for (Integer index : indexes) {
            text.newLine();
            text.write(index + "");
        }
        text.newLine();
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        chords();
        writeFile();
    }
}
