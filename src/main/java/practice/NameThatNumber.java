package practice;/*
ID: cravuri
LANG: JAVA
TASK: practice.namenum
*/
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 8/3/15
 */
class namenum {

    static List<String> possibleNames = new ArrayList<String>();
    static List<String> dictionary = new ArrayList<String>();
    static String serialNumber;

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("practice.namenum.in"));
        serialNumber = text.readLine();
    }

    public static void getDictionary() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("dict.txt"));
        String line;
        while ((line = text.readLine()) != null) {
            dictionary.add(line);
        }
    }

    public static String letterToNumber(String letter) {
        if (letter.equals("A") || letter.equals("B") || letter.equals("C")) {
            return "2";
        } else if (letter.equals("D") || letter.equals("E") || letter.equals("F")) {
            return "3";
        } else if (letter.equals("G") || letter.equals("H") || letter.equals("I")) {
            return "4";
        } else if (letter.equals("J") || letter.equals("K") || letter.equals("L")) {
            return "5";
        } else if (letter.equals("M") || letter.equals("N") || letter.equals("O")) {
            return "6";
        } else if (letter.equals("P") || letter.equals("R") || letter.equals("S")) {
            return "7";
        } else if (letter.equals("T") || letter.equals("U") || letter.equals("V")) {
            return "8";
        } else if (letter.equals("W") || letter.equals("X") || letter.equals("Y")) {
            return "9";
        }
        return "-";
    }

    public static void names() {
        for (String word : dictionary) {
            String number = "";
            for (int j = 0; j < word.length(); j++) {
                number += letterToNumber(String.valueOf(word.charAt(j)));
            }
            if (number.equals(serialNumber)) {
                possibleNames.add(word);
            }
        }
    }

    public static void writeFile() throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter("practice.namenum.out"));
        if (possibleNames.size() == 0) {
            text.write("NONE");
            text.newLine();
        } else {
            for (String name : possibleNames) {
                text.write(name);
                text.newLine();
            }
        }
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        getDictionary();
        names();
        writeFile();
    }
}
