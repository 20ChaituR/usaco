/*
ID: cravuri
LANG: JAVA
TASK: msquare
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

class msquare {

    StringBuilder t = new StringBuilder();
    StringBuilder s = new StringBuilder("12345678");

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("msquare.in"));
        for (int i = 0; i < 8; i++) {
            t.append(sc.next());
        }
    }

    void print(String moves) throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("msquare.out"));
        pw.println(moves.length());
        if (moves.length() <= 60) {
            pw.println(moves);
            pw.close();
            return;
        }
        for (int i = 0; i < moves.length() / 60; i++) {
            pw.println(moves.substring(i * 60, (i + 1) * 60));
        }
        pw.println(moves.substring(moves.length() / 60 * 60, (moves.length() / 60 * 60) + moves.length() % 60));
        pw.close();
    }

    void solve() throws IOException {
        PrintWriter pw = new PrintWriter(new FileWriter("msquare.out"));
        Queue<StringBuilder> q = new ArrayDeque<>();
        Set<String> v = new HashSet<>();
        q.add(new StringBuilder(s));
        q.add(new StringBuilder());
        while (!q.isEmpty()) {
            s = q.remove();
            StringBuilder moves = q.remove();
            if (v.contains(s.toString())) {
                continue;
            }
            v.add(s.toString());
            if (s.toString().equals(t.toString())) {
                print(moves.toString());
                return;
            }
            // A
            StringBuilder sa = new StringBuilder(s.toString());
            StringBuilder ma = new StringBuilder(moves.toString());
            q.add(sa.reverse());
            q.add(ma.append("A"));
            // B
            StringBuilder sb = new StringBuilder();
            StringBuilder mb = new StringBuilder(moves.toString());
            sb.append(s.substring(3, 4)).append(s.substring(0, 3)).append(s.substring(5, 8)).append(s.substring(4, 5));
            q.add(sb);
            q.add(mb.append("B"));
            // C
            StringBuilder sc = s;
            StringBuilder mc = new StringBuilder(moves.toString());
            String temp = sc.substring(1, 2);
            sc.replace(1, 2, sc.substring(6, 7));
            sc.replace(6, 7, sc.substring(5, 6));
            sc.replace(5, 6, sc.substring(2, 3));
            sc.replace(2, 3, temp);
            q.add(sc);
            q.add(mc.append("C"));
        }
    }

    public static void main(String[] args) throws IOException {
        msquare prob = new msquare();
        prob.readFile();
        prob.solve();
    }

}
