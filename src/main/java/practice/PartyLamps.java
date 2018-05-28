/*
ID: cravuri
LANG: JAVA
TASK: lamps
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class lamps {

    static int N;
    static int C;
    static int maxCount;
    static Set<Integer> ON = new HashSet<Integer>();
    static Set<Integer> OFF = new HashSet<Integer>();
    static Set<String> combos = new HashSet<String>();

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("lamps.in"));
        N = Integer.parseInt(text.readLine());
        C = Integer.parseInt(text.readLine());
        String[] N = text.readLine().split(" ");
        for (int i = 0; i < N.length - 1; i++) {
            ON.add(Integer.parseInt(N[i]));
        }
        String[] F = text.readLine().split(" ");
        for (int i = 0; i < F.length - 1; i++) {
            OFF.add(Integer.parseInt(F[i]));
        }
    }

    public static void solve2(int count, String s) {
        if (count >= maxCount) {
            boolean add = true;
            for (int x = 0; x < s.length(); x++) {
                if (ON.contains(x + 1) && s.charAt(x) == '0') {
                    add = false;
                    break;
                }
                if (OFF.contains(x + 1) && s.charAt(x) == '1') {
                    add = false;
                    break;
                }
            }
            if (add) {
                combos.add(s);
            }
            return;
        }
        String s1 = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                s1 = s1 + "1";
            } else {
                s1 = s1 + "0";
            }
        }
        solve2(count + 1, s1);
        s1 = "";
        for (int i = 0; i < s.length(); i += 2) {
            if (s.charAt(i) == '0') {
                s1 = s1 + "1";
            } else {
                s1 = s1 + "0";
            }
        }
        solve2(count + 1, s1);
        s1 = "";
        for (int i = 1; i < s.length(); i += 2) {
            if (s.charAt(i) == '0') {
                s1 = s1 + "1";
            } else {
                s1 = s1 + "0";
            }
        }
        solve2(count + 1, s1);
        s1 = "";
        for (int i = 0; i < s.length(); i += 3) {
            if (s.charAt(i) == '0') {
                s1 = s1 + "1";
            } else {
                s1 = s1 + "0";
            }
        }
        solve2(count + 1, s1);
    }

    public static void solve3() {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    for (int l = 0; l < 2; l++) {
                        String s = "";
                        for (int x = 1; x <= N; x++) {
                            boolean state = true;
                            if (i == 1) {
                                state = !state;
                            }
                            if (x % 2 == 1 && j == 1) {
                                state = !state;
                            }
                            if (x % 2 == 0 && k == 1) {
                                state = !state;
                            }
                            if (x % 3 == 1 && l == 1) {
                                state = !state;
                            }
                            if (state) {
                                s = s + "1";
                            } else {
                                s = s + "0";
                            }
                        }
                        boolean add = true;
                        for (int x = 0; x < s.length(); x++) {
                            if (ON.contains(x + 1) && s.charAt(x) == '0') {
                                add = false;
                                break;
                            }
                            if (OFF.contains(x + 1) && s.charAt(x) == '1') {
                                add = false;
                                break;
                            }
                        }
                        if (add) {
                            combos.add(s);
                        }
                    }
                }
            }
        }
    }

    public static void writeFile() throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter("lamps.out"));
        if (combos.size() == 0) {
            text.write("IMPOSSIBLE");
            text.newLine();
            text.close();
            return;
        }
        List<String> newCombos = new ArrayList<String>();
        for (String combo : combos) {
            newCombos.add(combo);
        }
        Collections.sort(newCombos);
        for (String combo : newCombos) {
            text.write(combo);
            text.newLine();
        }
        text.close();
    }

    public static void main(String [] args) throws IOException {
        readFile();
        if (C < 3) {
            maxCount = C;
            String s = "";
            for (int i = 0; i < N; i++) {
                s += "1";
            }
            solve2(0, s);
        } else {
            solve3();
        }
        writeFile();

    }

}
