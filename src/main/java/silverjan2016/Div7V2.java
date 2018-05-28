package silverjan2016;/*
ID: cravuri
LANG: JAVA
TASK: silverjan2016.Div7V2
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Div7V2 {

    static long[] cowIDs;

    public static void readFile() throws IOException {
        BufferedReader text = new BufferedReader(new FileReader("div7.in"));
        cowIDs = new long[Integer.parseInt(text.readLine())];
        for (int i = 0; i < cowIDs.length; i++) {
            cowIDs[i] = Integer.parseInt(text.readLine()) % 7;
        }
    }

    public static void maxSubsequence() {

    }

}
