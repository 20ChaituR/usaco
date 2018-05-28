/*
ID: cravuri
LANG: JAVA
TASK: preface
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

class preface {

    static int N;
    static int[] numerals = new int[7];
    static HashMap<String, Integer> roman = new HashMap<String, Integer>();
    static HashMap<Integer, String> values = new HashMap<Integer, String>();

    static void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("preface.in"));
        N = sc.nextInt();
    }

    static void addValues() {
        roman.put("I", 0);
        roman.put("V", 1);
        roman.put("X", 2);
        roman.put("L", 3);
        roman.put("C", 4);
        roman.put("D", 5);
        roman.put("M", 6);
        values.put(0, "I");
        values.put(1, "V");
        values.put(2, "X");
        values.put(3, "L");
        values.put(4, "C");
        values.put(5, "D");
        values.put(6, "M");
    }

    static String intToRoman(int input) {
        String s = "";
        while (input >= 1000) {
            s += "M";
            input -= 1000;        }
        while (input >= 900) {
            s += "CM";
            input -= 900;
        }
        while (input >= 500) {
            s += "D";
            input -= 500;
        }
        while (input >= 400) {
            s += "CD";
            input -= 400;
        }
        while (input >= 100) {
            s += "C";
            input -= 100;
        }
        while (input >= 90) {
            s += "XC";
            input -= 90;
        }
        while (input >= 50) {
            s += "L";
            input -= 50;
        }
        while (input >= 40) {
            s += "XL";
            input -= 40;
        }
        while (input >= 10) {
            s += "X";
            input -= 10;
        }
        while (input >= 9) {
            s += "IX";
            input -= 9;
        }
        while (input >= 5) {
            s += "V";
            input -= 5;
        }
        while (input >= 4) {
            s += "IV";
            input -= 4;
        }
        while (input >= 1) {
            s += "I";
            input -= 1;
        }
        return s;
    }

    static void solve() {
        for (int i = 1; i <= N; i++) {
            String s = intToRoman(i);
            for (int j = 0; j < s.length(); j++) {
                numerals[roman.get(s.charAt(j) + "")]++;
            }
        }
    }

    static void writeFile() throws IOException {
        PrintWriter pr = new PrintWriter(new FileWriter("preface.out"));
        for (int i = 0; i < 7; i++) {
            if (numerals[i] == 0) {
                break;
            }
            pr.println(values.get(i) + " " + numerals[i]);
        }
        pr.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        addValues();
        solve();
        writeFile();
    }

}
