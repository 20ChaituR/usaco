package practice;/*
ID: cravuri
LANG: JAVA
TASK: practice.beads
*/

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 7/20/15
 */
class beads {

    static String necklace;

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("practice.beads.in"));
        text.readLine();
        necklace = text.readLine();
    }

    public static int bead() {
        char curr = necklace.charAt(0);
        String newNecklace = "";
        int i = 0;
        while (curr == necklace.charAt(i) || necklace.charAt(i) == 'w') {
            if (i < necklace.length() - 1) {
                newNecklace = newNecklace + necklace.charAt(i);
                i++;
            } else {
                return necklace.length();
            }
        }
        if (curr == 'w') {
            curr = necklace.charAt(i);
            while (curr == necklace.charAt(i) || necklace.charAt(i) == 'w') {
                newNecklace = newNecklace + necklace.charAt(i);
                i++;
            }
        }
        String shiftedNecklace = necklace;
        shiftedNecklace = shiftedNecklace.substring(i);
        shiftedNecklace = shiftedNecklace + newNecklace;
        List<Integer> lengths = new ArrayList<Integer>();
        i = 0;
        while (i < shiftedNecklace.length()) {
            curr = shiftedNecklace.charAt(i);
            int length = 1;
            i++;
            while (i < shiftedNecklace.length() && (shiftedNecklace.charAt(i) == curr || shiftedNecklace.charAt(i) == 'w')) {
                i++;
                length++;
            }
            lengths.add(length);

        }
        int maxLength = -1;
        for (int n = 0; n < lengths.size() - 1; n++) {
            int x = lengths.get(n) + lengths.get(n + 1);
            if (x > maxLength) {
                maxLength = x;
            }
        }
        i = shiftedNecklace.length() - 1;
        List<Integer> lengthsBack = new ArrayList<Integer>();
        while (i >= 0) {
            curr = shiftedNecklace.charAt(i);
            int length = 1;
            if (curr == 'w') {
                length = 1;
                i--;
                length++;
                while (i >= 0 && shiftedNecklace.charAt(i) == 'w') {
                    i--;
                    length++;
                }
            }
            curr = shiftedNecklace.charAt(i);
            i--;
            while (i >= 0 && (shiftedNecklace.charAt(i) == curr || shiftedNecklace.charAt(i) == 'w')) {
                i--;
                length++;
            }
            lengthsBack.add(length);
        }
        for (int n = 0; n < lengthsBack.size() - 2; n++) {
            int x = lengthsBack.get(n) + lengthsBack.get(n + 1);
            if (x > maxLength) {
                maxLength = x;
            }
        }
        int x = lengthsBack.get(0) + lengthsBack.get(lengthsBack.size() - 1);
        if (maxLength < x) {
            maxLength = x;
        }
        return maxLength;
    }

    public static void writeFile(int length) throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter("practice.beads.out"));
        text.write(length + "");
        text.newLine();
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
//        necklace = "rwrwrwbwrwrwbwrwr";
        writeFile(bead());
        System.exit(0);
    }
}
