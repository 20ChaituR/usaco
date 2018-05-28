/*
ID: cravuri
LANG: JAVA
TASK: runround
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class runround {

    static int M;

    static void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("runround.in"));
        M = sc.nextInt();
    }

    static int solve() {
        int length = Integer.toString(M).length();
        for (int i = M + 1; i < Math.pow(10, length + 1); i++) {
            if (isRunaround(i)) {
                return i;
            }
        }
        return -1;
    }

    static boolean isRunaround(int x) {
        String num = Integer.toString(x);
        int length = num.length();
        int pos = 0;
        Set<String> positions = new HashSet<String>();
        for (int i = 0; i < length; i++) {
            if (positions.contains(num.charAt(pos) + "")) {
                return false;
            }
            positions.add(num.charAt(pos) + "");
            int loop = Integer.parseInt(num.charAt(pos) + "");
            for (int j = 0; j < loop; j++) {
                if (pos < length - 1) {
                    pos++;
                } else {
                    pos = 0;
                }
            }
        }
        return pos == 0;
    }

    static void writeFile(int x) throws IOException {
        PrintWriter pr = new PrintWriter(new FileWriter("runround.out"));
        pr.println(x);
        pr.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        writeFile(solve());
    }

}
