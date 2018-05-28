/*
ID: cravuri
LANG: JAVA
TASK: prefix
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

class prefix {

    private static String sequence = "";
    private static Set<String> primitives = new HashSet<String>();

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("prefix.in"));
        String line = text.readLine();
        while (!line.equals(".")) {
            String[] a = line.split(" ");
            Collections.addAll(primitives, a);
            line = text.readLine();
        }
        StringBuilder sb = new StringBuilder();
        while ((line = text.readLine()) != null) {
            sb.append(line);
        }
        sequence = sb.toString();
    }

    private static int longPrefix() {
        boolean[] dp = new boolean[sequence.length() + 1];
        dp[0] = true;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = false;
            for (String primitive : primitives) {
                if (i - primitive.length() < 0) {
                    continue;
                }
                if (!sequence.substring(i - primitive.length(), i).equals(primitive)) {
                    continue;
                }
                if (dp[i - primitive.length()]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        for (int i = sequence.length(); i >= 0; i--) {
            if (dp[i]) {
                return i;
            }
        }
        return -1;
    }

    public static void writeFile(int longPrefix) throws IOException {
        PrintWriter pr = new PrintWriter(new FileWriter("prefix.out"));
        pr.println(longPrefix);
        pr.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        writeFile(longPrefix());
    }

}