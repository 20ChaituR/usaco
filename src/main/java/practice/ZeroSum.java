/*
ID: cravuri
LANG: JAVA
TASK: zerosum
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

class zerosum {

    static int n;
    static int[] e = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    static String ans = "";

    public static void readFile() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("zerosum.in"));
        n = sc.nextInt();
    }

    public static void dfs(int s, int sum, int num, int sign, String str) throws IOException {
        if(s == n) {
            if(sum + sign * num == 0) {
                ans += str + "\n";
            }
            return;
        }
        dfs(s + 1, sum, num * 10 + e[s], sign, str + " " + e[s]);
        dfs(s + 1, sum + sign * num, e[s], 1, str + "+" + e[s]);
        dfs(s + 1, sum + sign * num, e[s], -1, str + "-" + e[s]);
    }

    public static void writeFile() throws IOException {
        PrintWriter pr = new PrintWriter(new FileWriter("zerosum.out"));
        pr.print(ans);
        pr.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        dfs(1, 0, 1, 1, "1");
        writeFile();
    }

}
