package platopen2018;
/*
 * Created by cravuri on 3/25/18
 */

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public abstract class Grader {

    int N, K;
    int[] ID;
    int ind;
    int pass;

    int[] notebook = new int[5500];

    public void run() throws FileNotFoundException {
        Scanner sc = new Scanner(new FileReader("bessie.in"));
        N = sc.nextInt();
        K = sc.nextInt();
        ID = new int[N];
        for (int i = 0; i < N; i++) {
            ID[i] = sc.nextInt();
        }
        for (pass = 0; pass <= 1; pass++) {
            for (ind = 0; ind < N; ind++) {
                helpBessie(ID[ind]);
            }
        }
    }

    int get(int index) {
        return notebook[index];
    }

    void set(int index, int value) {
        notebook[index] = value;
    }

    void shoutMinimum(int output) {
        System.out.println(output);
    }

    int getTrainLength() {
        return N;
    }

    int getWindowLength() {
        return K;
    }

    int getCurrentCarIndex() {
        return ind;
    }

    int getCurrentPassIndex() {
        return pass;
    }

    public abstract void helpBessie(int ID);
}
