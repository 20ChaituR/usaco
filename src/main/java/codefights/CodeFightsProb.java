package codefights;/*
ID: cravuri
LANG: JAVA
TASK: CodeFightsProb
*/

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class CodeFightsProb {

    static void input(int n, int maxVal) throws IOException {
        PrintWriter pr = new PrintWriter(new FileWriter("codefights.in"));
        pr.print("[");
        for (int i = 0; i < n; i++) {
            pr.print((int) (maxVal*Math.random()) + (i == n - 1 ? "]" : ","));
        }
    }

    static void output() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("codefights.in"));
        String[] in = sc.next().split(",");
        in[0] = in[0].substring(1);
        in[in.length - 1] = in[in.length - 1].substring(0, in[in.length - 1].length() - 1);
        int[] experiences = new int[in.length];
        for (int i = 0; i < experiences.length; i++) {
            experiences[i] = Integer.parseInt(in[1]);
        }
        int[] copyExperiences = new int[experiences.length];
        System.arraycopy(experiences, 0, copyExperiences, 0, experiences.length);
        Arrays.sort(copyExperiences);
        for (int i = 0; i < copyExperiences.length; i++) {
            
        }
    }

}
