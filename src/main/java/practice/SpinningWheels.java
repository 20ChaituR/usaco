/*
ID: cravuri
LANG: JAVA
TASK: spin
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.BitSet;
import java.util.Scanner;

class spin {

    BitSet[] wheels;
    int[] speeds;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("spin.in"));
        wheels = new BitSet[5];
        for (int i = 0; i < wheels.length; i++) {
            wheels[i] = new BitSet(360);
            for (int j = 0; j < 360; j++) {
                wheels[i].set(j, false);
            }
        }
        speeds = new int[5];
        for (int i = 0; i < 5; i++) {
            speeds[i] = sc.nextInt();
            int wedgeNum = sc.nextInt();
            int align, width;
            for (int j = 0; j < wedgeNum; j++) {
                align = sc.nextInt();
                width = sc.nextInt();
                for (int k = align; k <= align + width; k++) {
                    wheels[i].set(k % 360, true);
                }
            }
        }
    }

    void solve() throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("spin.out"));
        for (int t = 0; t < 10000; t++) {
            BitSet temp = new BitSet(360);
            for (int i = 0; i < 360; i++) {
                temp.set(i);
            }
            for (int i = 0; i < 5; i++) {
                temp.and(wheels[i]);
            }
            if (!temp.isEmpty()) {
                pw.println(t);
                pw.close();
                return;
            }
            for (int j = 0; j < 5; j++) {
                temp = wheels[j];
                wheels[j] = new BitSet(360);
                for (int k = 0; k < 360; k++) {
                    wheels[j].set((k + speeds[j]) % 360, temp.get(k));
                }
            }
        }
        pw.println("none");
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        spin prob = new spin();
        prob.readFile();
        prob.solve();
    }

}
