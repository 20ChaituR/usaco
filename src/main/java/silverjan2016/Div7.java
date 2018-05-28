package silverjan2016;/*
ID: cravuri
LANG: JAVA
TASK: div7
*/

import java.io.*;

public class Div7 {

    static long[] cowIDs;

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("div7.in"));
        cowIDs = new long[Integer.parseInt(text.readLine())];
        long count = 0L;
        for (int i = 0; i < cowIDs.length; i++) {
            count = count + Integer.parseInt(text.readLine());
            cowIDs[i] = count;
        }
    }

    public static long maxSubsequence() {
        Long maxSubsequence = 0L;
        for (int i = 0; i < cowIDs.length - 1; i++) {
            for (int j = i + 1; j < cowIDs.length; j++) {
                if ((cowIDs[j] - cowIDs[i]) % 7 == 0) {
                    if (j - i > maxSubsequence) {
                        maxSubsequence = (long) (j - i);
                    }
                }
            }
        }
        return maxSubsequence;
    }

    public static void writeFile(long max) throws IOException {
        BufferedWriter text = new BufferedWriter(new FileWriter("div7.out"));
        text.write(max + "");
        text.newLine();
        text.close();
    }

    public static void main(String[] args) throws IOException {
        readFile();
        writeFile(maxSubsequence());
    }

}
