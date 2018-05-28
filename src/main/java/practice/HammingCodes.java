/*
ID: cravuri
LANG: JAVA
TASK: hamming
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class hamming {

    static int N;
    static int B;
    static int D;
    static List<Integer> codeWords = new ArrayList<Integer>();

    public static void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("hamming.in"));
        N = sc.nextInt();
        B = sc.nextInt();
        D = sc.nextInt();
    }

    public static void solve() {
        codeWords.add(0);
        for (int i = 1; i < 1 << B; i++) {
            if (codeWords.size() >= N) {
                break;
            }
            boolean hammed = true;
            for (int j = 0; j < codeWords.size(); j++) {
                if (hammingDistance(i, codeWords.get(j)) < D) {
                    hammed = false;
                }
            }
            if (hammed) {
                codeWords.add(i);
            }
        }
    }

    public static int hammingDistance(int a, int b) {
        int count = 0;
        String s1 = Integer.toBinaryString(a);
        String s2 = Integer.toBinaryString(b);
        int x = s1.length();
        for (int i = 0; i < 8 - x; i++) {
            s1 = "0" + s1;
        }
        x = s2.length();
        for (int i = 0; i < 8 - x; i++) {
            s2 = "0" + s2;
        }
        for (int i = 0; i < 8; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                count++;
            }
        }
        return count;
    }

    public static void writeFile() throws IOException {
        PrintWriter pr = new PrintWriter(new FileWriter("hamming.out"));
        pr.print(codeWords.get(0));
        for (int i = 1; i < codeWords.size(); i++) {
            if (i % 10 == 0 && i != 0) {
                pr.println();
                pr.print(codeWords.get(i));
            } else {
                pr.print(" " + codeWords.get(i));
            }
        }
        pr.println();
        pr.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        solve();
        writeFile();
    }

}
