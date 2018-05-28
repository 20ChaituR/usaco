/*
ID: cravuri
LANG: JAVA
TASK: fact4
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

class fact4 {

    int N;

    void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("fact4.in"));
        N = sc.nextInt();
    }

    void solve() throws IOException {
        int b = 1;
        int mod = 100000;
        for (int i = 2; i <= N; i++) {
            b = b * i % mod;
            while (b % 10 == 0) {
                b /= 10;
            }
        }
        PrintWriter pw = new PrintWriter(new FileWriter("fact4.out"));
        pw.println(b % 10);
        pw.close();
    }

    public static void main(String[] args) throws IOException {
        fact4 prob = new fact4();
        prob.readFile();
        prob.solve();
    }

}
